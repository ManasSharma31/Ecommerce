package com.manas.notification_service.Kafka.Order;


import java.math.*;
import java.util.*;

import com.manas.notification_service.Kafka.Payment.PaymentMode;

public record OrderNotification(
    String reference,    
    BigDecimal totalPrice,
    List<ProductPurchaseReponse> products,
    PaymentMode paymentMode,
    CustomerResponse customer
) {
    
}
