package com.manas.customerservice.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

//this annotation will help us in creating equals and hash code method considering variables from Runtime exceptions classes.
@EqualsAndHashCode(callSuper  = true)
@Data
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;
}

