package com.manas.order_service.Model;


public record OrderLineRequest( Integer id,
     Integer orderId,
     Integer productId,
     Integer quantity) {
    
}
