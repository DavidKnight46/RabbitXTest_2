package org.rabbitx.rabbitbetest.exception;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String msg){
        super(msg);
    }
}
