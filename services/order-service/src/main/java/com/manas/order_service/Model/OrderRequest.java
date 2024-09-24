package com.manas.order_service.Model;

import java.math.*;
import java.util.List;

import com.manas.order_service.Entity.PaymentMode;
import com.manas.order_service.Product.ProductPurchaseRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public record OrderRequest(
    Integer id,
    String reference,
    @Positive(message = "Price should be positive")
    BigDecimal totalPrice,

    @NotNull(message = "Customer shouldn't be null")
    @NotEmpty(message = "Customer id should be blank")
    String customerId,

    @NotNull(message = "Payment mode should be selected to create the order")
    PaymentMode payMethodMode,

    @NotNull(message = "Product list shouldn't be null")
    @NotEmpty(message = "Atleast one product should be seelcted")
    @Valid
    List<ProductPurchaseRequest> products
) {
    
}