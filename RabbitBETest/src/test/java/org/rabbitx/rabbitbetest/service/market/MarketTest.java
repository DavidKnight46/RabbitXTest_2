package org.rabbitx.rabbitbetest.service.market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarketTest {

    private final double CURRENT_MARKET_PRICE = 2.0;
    private final double ENTRY_MARKET_PRICE = 1.0;
    private final double EXPECTED_RESULT = 1.0;

    private Market underTest;

    @BeforeEach
    public void init(){
        underTest = new ETCMarket(CURRENT_MARKET_PRICE, ENTRY_MARKET_PRICE);
    }

    @Test
    void calcuateMarketValue() {
        var result = underTest.calcuateMarketValue();

        assertEquals(EXPECTED_RESULT, result);
    }
}