package org.rabbitx.rabbitbetest.service.orderbook;

import org.rabbitx.rabbitbetest.models.NewTrade;
import org.rabbitx.rabbitbetest.repository.position.PositionEntity;

import java.util.List;

public interface OrderBookI {

    void processAnOrder(String user, String walletName, NewTrade newTrade);

    List<PositionEntity> getAllPositions(String user);
}
