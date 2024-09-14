package com.manas.order_service.Product;



import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

@NotNull(message = "Product id can't be null for purchasing a product")
Integer productId,
@NotNull(message = "Product quantity can't be null for purchasing a product")
Integer quantity
){}
