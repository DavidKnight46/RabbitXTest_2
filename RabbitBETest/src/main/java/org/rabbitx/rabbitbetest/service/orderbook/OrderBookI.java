package org.rabbitx.rabbitbetest.service.orderbook;

import org.rabbitx.rabbitbetest.models.NewTrade;
import org.rabbitx.rabbitbetest.models.OrderBook;

import java.util.List;

public interface OrderBookI {

    void processAnOrder(String user, String walletName, NewTrade newTrade);

    List<OrderBook> getAllPositions(String user);
}
