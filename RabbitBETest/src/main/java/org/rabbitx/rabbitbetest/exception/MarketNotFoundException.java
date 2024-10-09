package org.rabbitx.rabbitbetest.exception;

public class MarketNotFoundException extends RuntimeException{
    public MarketNotFoundException(String msg){
        super(msg);
    }
}
