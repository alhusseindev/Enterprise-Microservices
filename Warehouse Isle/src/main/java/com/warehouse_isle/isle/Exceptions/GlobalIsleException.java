package com.warehouse_isle.isle.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalIsleException extends ResponseEntityExceptionHandler {
    private static Logger myLogger = Logger.getLogger(String.valueOf(IsleException.class));

    @ExceptionHandler(IsleException.class)
    public ResponseEntity<Object> isleExceptionHandler(IsleException myIsleException, WebRequest myWebRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", "Isle Error!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
