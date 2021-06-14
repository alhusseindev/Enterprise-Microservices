package com.customers.customers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalCustomerException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<Object> globalCustomerException(CustomerException myCustomerException, WebRequest myWebRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Customer Error!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
