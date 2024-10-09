package org.rabbitx.rabbitbetest.service.market;

import org.springframework.stereotype.Component;

@Component
public class ETCMarket extends Market{

    public ETCMarket(){
        super();
    }

    public ETCMarket(double currentMarketPrice, double entryMarketPrice){
        super(currentMarketPrice, entryMarketPrice);
    }
}
