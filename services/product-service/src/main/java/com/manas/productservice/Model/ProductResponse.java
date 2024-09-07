package com.manas.productservice.Model;

import java.math.BigDecimal;
public record ProductResponse(
     Integer id,
     String name,
     String description,
     Integer quantity,
     BigDecimal price,
     Integer categoryId,
     String categoryName,
     String categoryDescription
) {
} 