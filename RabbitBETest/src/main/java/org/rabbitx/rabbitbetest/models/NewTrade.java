package org.rabbitx.rabbitbetest.models;

public record NewTrade(double sizeOfPosition,
                       TypeOfPosition type,
                       String marketName,
                       boolean isExecuted) {
}
