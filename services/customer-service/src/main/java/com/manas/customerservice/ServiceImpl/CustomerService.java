package com.manas.customerservice.ServiceImpl;


import com.manas.customerservice.Model.CustomerRequest;
import com.manas.customerservice.Model.CustomerResponse;
import java.util.List;  
public interface CustomerService {
    
    String createCustomer(CustomerRequest customerRequest);
    void updateCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getCustomers();
    CustomerResponse getCustomerById(String id);
    void deleteCustomer(String id);

}
