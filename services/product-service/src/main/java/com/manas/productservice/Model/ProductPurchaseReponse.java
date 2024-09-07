package com.manas.productservice.Model;

import java.math.BigDecimal;

public record ProductPurchaseReponse(Integer id,String name,String desciption,BigDecimal price,Integer quantity) {
    
}
