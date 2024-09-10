package com.manas.order_service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.manas.order_service.Customer.CustomerResponse;
import com.manas.order_service.Customer.FeignCustomerService;
import com.manas.order_service.Exception.BusinessException;
import com.manas.order_service.Model.OrderRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderService {

    private final FeignCustomerService customerClient;

    public Integer createOrder(OrderRequest request) {

        //check if the customer is valid 
        Optional<CustomerResponse>customer=customerClient.getCustomerById(request.customerId());        
        if(!customer.isPresent()) {
            throw new BusinessException("Customer is not present with id " + request.customerId());
        }
        
        return 1;
    }



    
}
