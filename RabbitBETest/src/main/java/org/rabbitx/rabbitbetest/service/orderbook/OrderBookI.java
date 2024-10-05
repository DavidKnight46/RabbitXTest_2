package org.rabbitx.rabbitbetest.service.orderbook;

import org.rabbitx.rabbitbetest.models.NewTrade;

public interface OrderBookI {

    void processAnOrder(String user, String walletName, NewTrade newTrade);

}
