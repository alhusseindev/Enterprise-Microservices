package com.employees_hr.employees.Exceptions;

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
public class GlobalEmployeeException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Object> globalEmployeeException(EmployeeException myEmployeeException, WebRequest myRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", "Employee HR Error!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
