package com.example.payment_service.Kafka;

import com.example.payment_service.Model.*;

import java.math.BigDecimal;

public record PaymentNotification(
    String orderRefString, 
    BigDecimal amount,
    PaymentMode paymentMode,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}



