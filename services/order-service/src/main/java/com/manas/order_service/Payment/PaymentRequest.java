package com.manas.order_service.Payment;

import java.math.BigDecimal;

import com.manas.order_service.Customer.CustomerResponse;
import com.manas.order_service.Entity.PaymentMode;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest( 
    BigDecimal amount,
    PaymentMode paymentMode,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
    ) {
    
}
