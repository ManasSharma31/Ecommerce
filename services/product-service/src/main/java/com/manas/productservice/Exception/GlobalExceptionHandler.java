
package com.manas.productservice.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

   
    @ExceptionHandler(ProductNotFoundException.class)
public ResponseEntity<String> handle(ProductNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

}

    @ExceptionHandler(value= {ProductException.class})
	public ResponseEntity<String> handleExceptions(ProductException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
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
