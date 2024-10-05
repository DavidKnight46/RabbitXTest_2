package org.rabbitx.rabbitbetest.service.orderbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;

import static org.junit.jupiter.api.Assertions.*;

class OrderBookITest {

    private final double WalletBalance = 1.0;

    private OrderBookI underTest;

    @BeforeEach
    public void init(){
        underTest = new OrderBookImpl(null, null);
    }

    @Test
    void orderBook_placePosition_Success_WithShort() {
        //underTest.placeAPosition(TypeOfPosition.SHORT, 3.0, 2.0, 100);

        //assertEquals(-6.0, result);
    }

    @Test
    void orderBook_placePosition_Success_WithLong() {
        //underTest.placeAPosition(TypeOfPosition.SHORT, 3.0, 2.0, 100);

        //assertEquals(2.0, result);
    }


}