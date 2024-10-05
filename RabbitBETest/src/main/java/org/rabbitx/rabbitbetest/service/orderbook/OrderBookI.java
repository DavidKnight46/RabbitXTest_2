package org.rabbitx.rabbitbetest.service.orderbook;

public interface OrderBookI {

    void processAnOrder(String user, String walletName, double sizeOfPosition, String nameOfMarketToTrade);

}
