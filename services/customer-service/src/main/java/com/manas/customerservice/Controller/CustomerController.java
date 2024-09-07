package com.manas.customerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manas.customerservice.Model.CustomerRequest;
import com.manas.customerservice.Model.CustomerResponse;
import com.manas.customerservice.ServiceImpl.CustomerService;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //valid annonatation checks for the validation provided in the CustomerRequest class and any paramter is violating that rule , a MethodArgumentNotValidException will be thrown whihc I have handled in my Global class.
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody
        @Valid CustomerRequest customerRequest){

        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody
        @Valid CustomerRequest customerRequest){
            customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers(){

        return ResponseEntity.ok(customerService.getCustomers());
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }
}
