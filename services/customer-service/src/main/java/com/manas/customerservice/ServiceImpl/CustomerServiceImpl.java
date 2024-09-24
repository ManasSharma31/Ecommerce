package com.manas.customerservice.ServiceImpl;

import org.springframework.stereotype.Service;

import com.manas.customerservice.Entity.Customer;
import com.manas.customerservice.Exception.CustomerNotFoundException;
import com.manas.customerservice.Mapper.CustomerMapper;
import com.manas.customerservice.Model.CustomerRequest;
import com.manas.customerservice.Model.CustomerResponse;
import com.manas.customerservice.Repository.CustomerRepository;
import java.util.*;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    @Override
    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer = mapper.toCustomer(customerRequest);
        return customerRepository.save(customer).getId();
    }
    @Override
    public void updateCustomer(CustomerRequest customerRequest) {
        String customerId=customerRequest.id();
        Customer customer=customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        if(StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address()!=null ){
            customer.setAddress(customerRequest.address());
        }
        customerRepository.save(customer);
    }
    @Override
    public List<CustomerResponse> getCustomers() {
        List<CustomerResponse> customersResponseList = new ArrayList<>();
        customerRepository.findAll().forEach(customer->{
            CustomerResponse customerResponse=mapper.toCustomerResponse(customer);
            customersResponseList.add(customerResponse);
        });
        return customersResponseList;
    }
    @Override
    public CustomerResponse getCustomerById(String id) {
         return customerRepository.findById(id)
        .map(customer -> mapper.toCustomerResponse(customer))
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found with the requested "+id));
    }
    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
