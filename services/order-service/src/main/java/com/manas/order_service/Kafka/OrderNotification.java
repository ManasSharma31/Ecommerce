package com.manas.order_service.Kafka;
import java.math.*;

import java.util.*;

import com.manas.order_service.Customer.CustomerResponse;
import com.manas.order_service.Entity.PaymentMode;
import com.manas.order_service.Product.ProductPurchaseReponse;

public record OrderNotification(
    String reference,
    BigDecimal totalPrice,
    List<ProductPurchaseReponse>products,
    PaymentMode paymentMode,
    CustomerResponse customer
) {
    
}
