package com.manas.order_service.Product;


import java.math.BigDecimal;

public record ProductPurchaseReponse(Integer id,
String name,
String description,
Integer quantity,
BigDecimal price){}

