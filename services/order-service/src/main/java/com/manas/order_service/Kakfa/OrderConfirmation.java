package com.manas.order_service.Kakfa;
import java.math.*;

import java.util.*;

import com.manas.order_service.Customer.CustomerResponse;
import com.manas.order_service.Entity.PayMethodMode;
import com.manas.order_service.Product.ProductPurchaseReponse;

public record OrderConfirmation(
    String reference,
    BigDecimal totalPrice,
    List<ProductPurchaseReponse>prouducts,
    PayMethodMode payMethodMode,
    CustomerResponse customer
) {
    
}
