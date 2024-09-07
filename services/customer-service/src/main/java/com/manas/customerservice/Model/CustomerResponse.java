package com.manas.customerservice.Model;
import com.manas.customerservice.Entity.Address;


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
    private Address address;

}
