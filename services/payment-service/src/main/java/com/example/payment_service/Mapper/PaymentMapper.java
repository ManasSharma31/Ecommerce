package com.example.payment_service.Mapper;

import org.springframework.stereotype.Service;

import com.example.payment_service.Entity.Payment;
import com.example.payment_service.Model.PaymentRequest;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
        .amount(request.amount())
        .orderId(request.orderId())
        .paymentMode(request.paymentMode())
        .build();
    }
    


}
