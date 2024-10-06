package org.rabbitx.rabbitbetest.service.orderbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.rabbitx.rabbitbetest.exception.InsufficientBalanceInWalletException;
import org.rabbitx.rabbitbetest.exception.MarketNotFoundException;
import org.rabbitx.rabbitbetest.exception.NoUserFoundException;
import org.rabbitx.rabbitbetest.models.NewTrade;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;
import org.rabbitx.rabbitbetest.repository.position.MarketEntity;
import org.rabbitx.rabbitbetest.repository.position.MarketRepository;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.repository.user.UserEntity;
import org.rabbitx.rabbitbetest.repository.user.UserRepository;
import org.rabbitx.rabbitbetest.repository.wallet.Transactions;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderBookITest {

    private final String USER = "testUser";
    private final String WALLET_NAME = "zackswallet";
    private final double SIZE_OF_POSITION = 1.0;
    private final String MARKET_NAME = "WALLMARKET";
    private final String OTHER_MARKET_NAME = "FISHERMAN_HORIZON";
    private final int LEVERAGE = 10;
    private final TypeOfPosition SHORT = TypeOfPosition.SHORT;
    private final TypeOfPosition LONG = TypeOfPosition.LONG;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MarketRepository marketRepository;

    private OrderBookI underTest;

    @BeforeEach
    public void init(){
        underTest = new OrderBookImpl(userRepository, marketRepository, 10, LEVERAGE);
    }

    @Test
    void orderBook_placePosition_Success_WithShort() {
        when(userRepository.findByUserName(USER)).thenReturn(Optional.of(createUserEntity(100)));
        when(marketRepository.findByMarketName(MARKET_NAME)).thenReturn(Optional.of(createMarket()));

        underTest.processAnOrder(USER, WALLET_NAME, new NewTrade(SIZE_OF_POSITION, SHORT,MARKET_NAME,true));

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void orderBook_placePosition_Success_WithLong() {
        when(userRepository.findByUserName(USER)).thenReturn(Optional.of(createUserEntity(100)));
        when(marketRepository.findByMarketName(MARKET_NAME)).thenReturn(Optional.of(createMarket()));

        underTest.processAnOrder(USER, WALLET_NAME, new NewTrade(SIZE_OF_POSITION, LONG, MARKET_NAME,true));

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void orderBook_placePosition_Failure_UserNotFound(){
        assertThrows(NoUserFoundException.class, () ->{
            underTest.processAnOrder(USER, WALLET_NAME, new NewTrade(SIZE_OF_POSITION, SHORT,MARKET_NAME,true));
        });
    }

    @Test
    void orderBook_placePosition_Failure_InsufficentBalanceInWallet(){
        when(userRepository.findByUserName(USER)).thenReturn(Optional.of(createUserEntity(0)));

        assertThrows(InsufficientBalanceInWalletException.class, () ->{
            underTest.processAnOrder(USER, WALLET_NAME, new NewTrade(SIZE_OF_POSITION, SHORT,MARKET_NAME,true));
        });
    }

    @Test
    void orderBook_placePosition_Failure_NoMarketFound(){
        when(userRepository.findByUserName(USER)).thenReturn(Optional.of(createUserEntity(100)));
        when(marketRepository.findByMarketName(MARKET_NAME)).thenReturn(Optional.empty());


        assertThrows(MarketNotFoundException.class, () ->{
            underTest.processAnOrder(USER, WALLET_NAME, new NewTrade(SIZE_OF_POSITION, SHORT,MARKET_NAME,true));
        });
    }

    private UserEntity createUserEntity(double startBalance){
        UserEntity entity = new UserEntity();
        entity.setWallets(createWallets(startBalance));
        entity.setPositions(createPositions());

        return entity;
    }

    private List<WalletEntity> createWallets(double startingBalance){
        WalletEntity wallet = new WalletEntity();
        wallet.setWalletName(WALLET_NAME);
        wallet.setCurrentBalance(startingBalance);
        wallet.setTransactions(createTransactionList());

        return List.of(wallet);
    }

    private ArrayList<PositionEntity> createPositions(){
        PositionEntity position = new PositionEntity();
        MarketEntity market = new MarketEntity();
        market.setMarketName(OTHER_MARKET_NAME);

        position.setMarket(market);

        ArrayList<PositionEntity> li = new ArrayList<>();
        li.add(position);

        return li;
    }

    private MarketEntity createMarket(){
        MarketEntity marketEntity = new MarketEntity();
        marketEntity.setId(1l);

        return marketEntity;
    }

    private ArrayList<Transactions> createTransactionList(){
        return new ArrayList<>();
    }
}