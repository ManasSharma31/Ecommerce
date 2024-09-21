package com.manas.notification_service.Kafka.Order;


import java.math.*;
import java.util.*;

import com.manas.notification_service.Kafka.Payment.PayMethodMode;

public record OrderConfirmation(
    String reference,    
    BigDecimal totalPrice,
    PayMethodMode payMethodMode,
    Customer customer,
    List<Product> products
) {
    
}
