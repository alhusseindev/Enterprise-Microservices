package com.accounting.accounting.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalAccountingException extends ResponseEntityExceptionHandler {

    public static Logger myLogger = Logger.getLogger(String.valueOf(GlobalAccountingException.class));

    @ExceptionHandler(AccountingException.class)
    public ResponseEntity<Object> globalAccountingException(AccountingException myAccException, WebRequest myRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Accounting Exception!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
