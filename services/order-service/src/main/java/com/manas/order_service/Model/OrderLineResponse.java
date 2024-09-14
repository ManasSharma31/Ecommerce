package com.manas.order_service.Model;


public record OrderLineResponse(
     Integer id,
     Integer productId,
     Integer quantity
) {
} 
