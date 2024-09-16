package com.example.payment_service.Model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
public record PaymentRequest(
    Integer id,
    @NotNull(message = "Amount cannot be null")
    BigDecimal amount,
    @NotNull(message = "Payment method not supported")
    PaymentMode paymentMode,
    @NotNull(message = "For payment there should be an order id ")
    Integer orderId,
    String orderReference,
    Customer customer
) {
    
}
