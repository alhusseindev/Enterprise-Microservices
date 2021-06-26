package com.warehouse.warehouse_management.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalWarehouseException extends ResponseEntityExceptionHandler {
    Logger myLogger = Logger.getLogger(String.valueOf(WarehouseException.class));

    public ResponseEntity<Object> globalWarehouseException(WarehouseException myWarehouseException, WebRequest myRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("time-stamp: ", LocalDateTime.now());
        body.put("message", "Warehouse Error Occurred!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
