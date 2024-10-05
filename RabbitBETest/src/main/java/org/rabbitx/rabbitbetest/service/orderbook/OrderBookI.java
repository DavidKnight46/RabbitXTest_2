package org.rabbitx.rabbitbetest.service.orderbook;

public interface OrderBookI {

    void processAnOrder(String user, String walletName, double sizeOfPosition, String nameOfMarketToTrade);

//    double calcuateunRealisedPNL(TypeOfPosition side, double sizeOfPosition, double currentMarketValue);
//
//    double calcuateAccountEquity(double walletBalance, double unrealisedPNL);
//
//    double calcuateTotalPositionalNotional(double accountEquity, double totalPositionNotional );
//
//    double calcuateAccountMargin(AtomicInteger sizeOfPosition, double currentMarketPrice);
}
