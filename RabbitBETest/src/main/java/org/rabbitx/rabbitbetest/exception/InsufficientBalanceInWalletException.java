package org.rabbitx.rabbitbetest.exception;

public class InsufficientBalanceInWalletException extends RuntimeException{
    public InsufficientBalanceInWalletException(String msg){
        super(msg);
    }
}
