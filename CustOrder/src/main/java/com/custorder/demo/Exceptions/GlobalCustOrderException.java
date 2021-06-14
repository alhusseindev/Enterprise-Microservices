package com.custorder.demo.Exceptions;

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
public class GlobalCustOrderException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustOrderException.class)
    public ResponseEntity<Object> globalCustOrderException(CustOrderException myCustOrderException, WebRequest myRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Customer Order Exception!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
