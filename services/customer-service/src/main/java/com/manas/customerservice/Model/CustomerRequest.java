package com.manas.customerservice.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import com.manas.customerservice.Entity.Address;

public record CustomerRequest(
    String id,
@NotNull(message = "First Name cannot be null")
 String firstName,
@NotNull(message = "Last Name cannot be null")
 String lastName,
@Email(message = "Email is not valid!")
@NotNull(message = "Email cannot be null")
 String email,
 Address address) {
    
}
