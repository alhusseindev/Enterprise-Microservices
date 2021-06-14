package com.bookings.bookings_appointments.Exceptions;

public class BookingsException extends Exception{
    public BookingsException(String errorMessage){
        super(errorMessage);
    }
}