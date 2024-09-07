package com.manas.productservice.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductNotFoundException extends RuntimeException  {
    
    public ProductNotFoundException(String message) {
        super(message);
    }
}
