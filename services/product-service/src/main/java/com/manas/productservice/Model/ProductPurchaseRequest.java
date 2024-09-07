
package com.manas.productservice.Model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

@NotNull(message = "Product id can't be null for purchasing a product")
Integer id,
@NotNull(message = "Product quantity can't be null for purchasing a product")
Integer quantity
){

}