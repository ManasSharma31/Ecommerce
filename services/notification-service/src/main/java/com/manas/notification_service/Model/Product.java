package com.manas.notification_service.Model;

import java.math.*;

public record Product( 
     String name,
     String description,
     Integer quantity,
     BigDecimal price){}
    
