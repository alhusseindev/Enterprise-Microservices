package com.bookings.bookings_appointments.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.lang.Object;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@ControllerAdvice
public class GlobalBookingsException extends ResponseEntityExceptionHandler {
    private static Logger myLogger = Logger.getLogger(String.valueOf(BookingsException.class));

    @ExceptionHandler(BookingsException.class)
    public ResponseEntity<Object> globalBookingsException(BookingsException myBookingsExcpetion , WebRequest myRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", "Bookings/Appointments Error!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
