package org.rabbitx.rabbitbetest.models;

import org.rabbitx.rabbitbetest.repository.position.PositionEntity;
import org.rabbitx.rabbitbetest.repository.wallet.WalletEntity;

import java.util.List;

public record OrderBook(String userName,
                        List<WalletEntity> wallets,
                        List<PositionEntity> positions) {
}
