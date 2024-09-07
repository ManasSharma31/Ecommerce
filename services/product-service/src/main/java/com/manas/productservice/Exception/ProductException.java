package com.manas.productservice.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper =true)
@Data
public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }
    
}
