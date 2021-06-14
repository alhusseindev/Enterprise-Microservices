package com.expenses.demo.Exceptions;

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
public class GlobalExpenseException extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ExpensesException.class)
    public ResponseEntity<Object> globalExpensesException(ExpensesException myExpensesException, WebRequest myRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Expense Exception!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
