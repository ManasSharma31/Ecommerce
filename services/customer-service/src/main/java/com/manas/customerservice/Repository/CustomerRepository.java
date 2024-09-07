package com.manas.customerservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manas.customerservice.Entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    
}
