package com.manas.order_service.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
