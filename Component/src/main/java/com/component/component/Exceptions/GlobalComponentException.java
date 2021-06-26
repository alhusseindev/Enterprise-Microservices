package com.component.component.Exceptions;

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
public class GlobalComponentException extends ResponseEntityExceptionHandler {
        private static Logger myLogger = Logger.getLogger(String.valueOf(ComponentException.class));

        @ExceptionHandler(ComponentException.class)
        public ResponseEntity<Object> globalComponentException(ComponentException myComponentException, WebRequest myWebRequest){
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Component Error!");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

}
