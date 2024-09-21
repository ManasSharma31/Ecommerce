package com.manas.notification_service.Kafka.Order;

import java.math.*;

public record Product( 
     String name,
     String description,
     Integer quantity,
     BigDecimal price){}
    
