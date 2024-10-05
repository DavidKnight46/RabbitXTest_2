package org.rabbitx.rabbitbetest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RabbitXExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InsufficientBalanceInWalletException.class)
    public ResponseEntity<Object> handleInsufficientBalanceInWalletException(InsufficientBalanceInWalletException e, WebRequest request){
        return createErrorResponse(e, request);
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleNoUserFoundException(NoUserFoundException e, WebRequest request){
        return createErrorResponse(e, request);
    }

    @ExceptionHandler(MarketNotFoundException.class)
    public ResponseEntity<Object> handleMarketNotFoundException(MarketNotFoundException e, WebRequest request){
        return createErrorResponse(e, request);
    }

    private ResponseEntity<Object> createErrorResponse(Exception e, WebRequest request) {
        return handleExceptionInternal(e,
                e.getMessage(),
                HttpHeaders.EMPTY,
                HttpStatus.NOT_FOUND,
                request);
    }
}
