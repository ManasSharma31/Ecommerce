package com.manas.order_service.Customer;

import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface FeignCustomerService {
    

    @GetMapping("/api/v1/customers/{id}")
    Optional<CustomerResponse> getCustomerById(@PathVariable String id);

}
