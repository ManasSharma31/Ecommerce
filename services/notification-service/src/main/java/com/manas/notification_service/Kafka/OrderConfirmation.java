package com.manas.notification_service.Kafka;


import java.math.*;
import java.util.*;

import com.manas.notification_service.Model.Customer;
import com.manas.notification_service.Model.PayMethodMode;
import com.manas.notification_service.Model.Product;

public record OrderConfirmation(
    String reference,    
    BigDecimal totalPrice,
    PayMethodMode payMethodMode,
    Customer customer,
    List<Product> products
) {
    
}
