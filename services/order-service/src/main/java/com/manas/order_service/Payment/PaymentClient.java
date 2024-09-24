package com.manas.order_service.Payment;

import org.springframework.cloud.openfeign.FeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payment-service")
public interface PaymentClient {
    
    @PostMapping("/api/v1/payments")
    @CircuitBreaker(name="payment-service",fallbackMethod="requestOrderPaymentFallback")
    Integer requestOrderPayment(@RequestBody PaymentRequest payment);

    default Integer requestOrderPaymentFallback(PaymentRequest payment, Throwable ex) {
        System.err.println("Payment request failed due to: " + ex.getMessage());
        return -1; // Placeholder for failed payment ID
    }
}
