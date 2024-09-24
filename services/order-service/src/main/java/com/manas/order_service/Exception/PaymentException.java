package com.manas.order_service.Exception;

public class PaymentException extends RuntimeException{
    public PaymentException(String message){
        super(message);
    }
}
