package com.manas.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.order_service.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {


    
}