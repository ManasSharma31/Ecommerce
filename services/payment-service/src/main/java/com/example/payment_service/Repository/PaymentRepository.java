package com.example.payment_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment_service.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

    
} 
