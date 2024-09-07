package com.manas.customerservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.*;


import com.manas.customerservice.Model.ErrorResponse;

import io.micrometer.common.util.StringUtils;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

   
    @ExceptionHandler(CustomerNotFoundException.class)
public ResponseEntity<String> handle(CustomerNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMsg());

}

    @ExceptionHandler(value= {Exception.class})
	public ResponseEntity<ExceptionModel> handleExceptions(Exception e){
		
		String message=e.getLocalizedMessage();
		if(StringUtils.isBlank(message)) {
			message=e.toString();
		}
		ExceptionModel x=new ExceptionModel(message,new Date());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(x);
		
		
	}

    @ExceptionHandler(value ={MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<String,String>();
        exception.getBindingResult().getAllErrors()
        .forEach(error->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
