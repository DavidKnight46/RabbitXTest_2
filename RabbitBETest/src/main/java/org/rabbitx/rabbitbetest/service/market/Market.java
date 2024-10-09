package org.rabbitx.rabbitbetest.service.market;

public abstract class Market {
    protected volatile double currentMarketPrice;
    protected volatile double entryMarketPrice;

    public Market(){
        this.currentMarketPrice = 1.0;
        this.entryMarketPrice = 1.0;
    }

    public Market(double currentMarketPrice, double entryMarketPrice){
        this.currentMarketPrice = currentMarketPrice;
        this.entryMarketPrice = entryMarketPrice;
    }

    public double calcuateMarketValue() {
        return currentMarketPrice - entryMarketPrice;
    }
}
