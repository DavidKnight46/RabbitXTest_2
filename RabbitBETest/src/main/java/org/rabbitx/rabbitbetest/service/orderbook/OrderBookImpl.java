package org.rabbitx.rabbitbetest.service.orderbook;

import org.rabbitx.rabbitbetest.models.TypeOfPosition;
import org.rabbitx.rabbitbetest.repository.user.UserRepository;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBookImpl implements OrderBookI{

    private final UserRepository userRepository;

    public OrderBookImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void processAnOrder(String user, String walletName, double sizeOfPosition, String nameOfMarketToTrade) {
        //TODO: check valid user
        List<WalletEntity> walletsList;

        if(userRepository.findByUserName(user).isPresent()){
            walletsList = userRepository.findByUserName(user).get().getWallets();
        } else {
            throw new IllegalArgumentException();
        }

        //TODO: get wallet and check amount
        WalletEntity foundWallet = walletsList.stream().filter(e -> e.getWalletName().contentEquals(walletName)).findFirst().get();
        if(foundWallet.getCurrentBalance() < sizeOfPosition){
            throw new IllegalArgumentException();
        }

        //TODO: check have a position on another market
        long count = userRepository.findByUserName(user).get().getPositions().stream().filter(e -> !e.getMarket().getMarketName().contentEquals("")).count();


        if(count >= 1){
            //TODO: execute order i.e. change wallet balance, create transaction, position/trade is executed, calcuate margin
        }


//        if(marginisbelow < 10){
//            //TODO: revert transaction, put money back in wallet
//        }

    }

    //@Override
    private double calcuateUnrealisedPNL(TypeOfPosition side, double sizeOfPosition, double currentMarketValue) {
        var value = switch (side){
            case LONG -> 1;
            case SHORT -> -1;
        };

        return value * sizeOfPosition * currentMarketValue;
    }

    //@Override
    private double calcuateAccountEquity(double walletBalance, double unrealisedPNL) {
        return walletBalance + unrealisedPNL;
    }

    //@Override
    private double calcuateTotalPositionalNotional(double accountEquity, double currentMarketValue) {
        return accountEquity * currentMarketValue;
    }

    //@Override
    private double calcuateAccountMargin(double accountEquity, double tpn) {
        return accountEquity / tpn;
    }
}
