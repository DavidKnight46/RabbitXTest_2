package org.rabbitx.rabbitbetest.service.orderbook;

import org.rabbitx.rabbitbetest.exception.BelowMarginException;
import org.rabbitx.rabbitbetest.exception.InsufficientBalanceInWalletException;
import org.rabbitx.rabbitbetest.exception.MarketNotFoundException;
import org.rabbitx.rabbitbetest.exception.NoUserFoundException;
import org.rabbitx.rabbitbetest.models.NewTrade;
import org.rabbitx.rabbitbetest.models.TransactionType;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;
import org.rabbitx.rabbitbetest.repository.position.MarketEntity;
import org.rabbitx.rabbitbetest.repository.position.MarketRepository;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;
import org.rabbitx.rabbitbetest.repository.user.UserRepository;
import org.rabbitx.rabbitbetest.repository.wallet.Transactions;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderBookImpl implements OrderBookI{

    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private static volatile Integer marginLimit;
    private static volatile Integer leverage;

    public OrderBookImpl(UserRepository userRepository,
                         MarketRepository marketRepository,
                         Integer getLimit,
                         Integer getLeverage){
        this.userRepository = userRepository;
        this.marketRepository = marketRepository;
        marginLimit = getLimit;
        leverage = getLeverage;
    }

    @Override
    public List<PositionEntity> getAllPositions(String user) {
        Iterable<UserEntity> all = userRepository.findAll();

        if(userRepository.findByUserName(user).isPresent()) {
            return userRepository.findByUserName(user).get().getPositions();
        } else {
            throw new NoUserFoundException(user + " not found.");
        }
    }

    @Transactional
    @Override
    public void processAnOrder(String activeUser, String walletName, NewTrade newTrade) {
        System.out.println("smurf");
        List<WalletEntity> walletsList;

        if(userRepository.findByUserName(activeUser).isPresent()){
            walletsList = userRepository.findByUserName(activeUser).get().getWallets();
        } else {
            throw new NoUserFoundException(activeUser + " not found.");
        }

        WalletEntity foundWallet = walletsList.stream().filter(e -> e.getWalletName().contentEquals(walletName)).findFirst().get();
        if(foundWallet.getCurrentBalance() < newTrade.sizeOfPosition()){
            throw new InsufficientBalanceInWalletException("Insufficient funds in wallet.");
        }

        boolean hasPositionOnOtherMarket = userRepository
                .findByUserName(activeUser)
                .get()
                .getPositions()
                .stream()
                .anyMatch(e -> !e.getMarket().getMarketName().contentEquals(newTrade.marketName()));

        if(hasPositionOnOtherMarket){
            var activeWallet = walletsList
                    .stream()
                    .filter(e -> e.getWalletName().contentEquals(walletName))
                    .findFirst()
                    .get();

            var newBalance = activeWallet.getCurrentBalance() - newTrade.sizeOfPosition();
            activeWallet.setCurrentBalance(newBalance);

            UserEntity activeUserEntity = userRepository
                    .findByUserName(activeUser)
                    .get();

            PositionEntity newPosition = new PositionEntity();
            newPosition.setExecuted(true);
            newPosition.setLeverage(leverage);

            List<PositionEntity> positions = activeUserEntity.getPositions();//;.add(newPosition);
            positions.add(newPosition);

            MarketEntity selectedMarketToTrade;

            if(marketRepository.findByMarketName(newTrade.marketName()).isPresent()) {
                selectedMarketToTrade = marketRepository.findByMarketName(newTrade.marketName()).get();
                newPosition.setMarketId(marketRepository.findByMarketName(newTrade.marketName()).get().getId());
            } else {
                throw new MarketNotFoundException(newTrade.marketName() + " does not exist.");
            }

            userRepository.findByUserName(activeUser).get().getPositions().add(newPosition);

            if(calcuateAccountMargin(newTrade, activeWallet, selectedMarketToTrade.getCurrentMarketPrice()) < marginLimit){
                createNewTransaction(newTrade, true, activeWallet);
                throw new BelowMarginException("Margin is below limit. Transaction reverted.");
            } else {
                createNewTransaction(newTrade, false, activeWallet);

                userRepository.save(activeUserEntity);
            }
        } else {
            throw new MarketNotFoundException("There is no other market.");
        }
    }

    private void createNewTransaction(NewTrade newTrade, boolean isReverted, WalletEntity activeWallet) {
        Transactions transactions = new Transactions();
        transactions.setAmount(newTrade.sizeOfPosition());
        transactions.setType(TransactionType.DEBIT.name());
        transactions.setDateOfTransaction(LocalDate.now());
        transactions.setReverted(isReverted);

        activeWallet.getTransactions().add(transactions);
    }

    private double calcuateAccountMargin(NewTrade record,
                                         WalletEntity activeWallet,
                                         double currentMarketValue) {
        double unrealisedPNL = calcuateUnrealisedPNL(record.type(), record.sizeOfPosition(), currentMarketValue);

        double accountEquity = calcuateAccountEquity(activeWallet.getCurrentBalance(), unrealisedPNL);

        return calcuateAccountEquity(activeWallet.getCurrentBalance(), unrealisedPNL) /
                calcuateTotalPositionalNotional(accountEquity, currentMarketValue);
    }

    private double calcuateUnrealisedPNL(TypeOfPosition side, double sizeOfPosition, double currentMarketValue) {
        var sideValue = switch (side){
            case LONG -> 1;
            case SHORT -> -1;
        };

        return sideValue * sizeOfPosition * currentMarketValue;
    }

    private double calcuateAccountEquity(double walletBalance, double unrealisedPNL) {
        return walletBalance + unrealisedPNL;
    }

    private double calcuateTotalPositionalNotional(double accountEquity, double currentMarketValue) {
        return accountEquity * currentMarketValue;
    }
}
