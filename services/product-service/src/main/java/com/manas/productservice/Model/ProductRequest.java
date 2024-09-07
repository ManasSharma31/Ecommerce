
package com.manas.productservice.Model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(

     Integer id,
     @NotNull(message = "Product name cannot be null")
     String name,
     @NotNull(message = "Product description cannot be null")
     String description,
     @Positive(message = "Product quantity should be positive")
     @NotNull(message = "Product quantity sholdn't be null")
     Integer quantity,
     @NotNull(message = "Product price cannot be null")
     @Positive(message = "Product price should be positive")
     BigDecimal price,
     @NotNull(message = "Product category ID cannot be null")
     Integer categoryId
) {
}