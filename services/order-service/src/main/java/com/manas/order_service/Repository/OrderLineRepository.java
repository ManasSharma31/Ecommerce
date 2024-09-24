package com.manas.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.manas.order_service.Entity.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Integer>{

    List<OrderLine> findAllByOrderId(Integer orderId);
    
}
