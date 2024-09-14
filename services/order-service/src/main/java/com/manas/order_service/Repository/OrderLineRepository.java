package com.manas.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import com.manas.order_service.Entity.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer>{

    List<OrderLine> findAllByOrderId(String orderId);
    
}
