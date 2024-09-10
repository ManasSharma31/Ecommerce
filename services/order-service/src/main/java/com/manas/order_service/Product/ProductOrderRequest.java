package com.manas.order_service.Product;

import jakarta.validation.constraints.NotNull;

public record ProductOrderRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @NotNull(message = "Quantity is required to make an order")
    Integer quantity
   
 
) {
    
}
