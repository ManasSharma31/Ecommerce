package com.example.payment_service.Model;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
//if we use a class reference in other class we should mark it as validated than only the customer object will be validated in Payment class.
public record Customer( 
    String id,
@NotNull(message ="Customer first name can not be null") 
String firstName,
@NotNull(message = "Customer laste name can not be null")
 String lastName,
 @NotNull(message = "Customer email can not be null")
 @Email(message = "Customer email is not valid")
 String email) {
} 