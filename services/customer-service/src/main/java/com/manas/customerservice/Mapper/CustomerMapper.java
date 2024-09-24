package com.manas.customerservice.Mapper;

import org.springframework.stereotype.Component;

import com.manas.customerservice.Entity.Address;
import com.manas.customerservice.Entity.Customer;
import com.manas.customerservice.Model.CustomerRequest;
import com.manas.customerservice.Model.CustomerResponse;

@Component
public class CustomerMapper {
    
    public Customer toCustomer(CustomerRequest req){
        return Customer.builder()
        .id(req.id())
        .firstName(req.firstName())
        .lastName(req.lastName())
        .email(req.email())
        .address(Address.builder().houseNumber(req.address().getHouseNumber()).pinCode(req.address().getPinCode()).build())
        .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .address(Address.builder().houseNumber(customer.getAddress().getHouseNumber()).pinCode(customer.getAddress().getPinCode()).build())
        .build();
    }
}
