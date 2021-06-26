package com.bookings.bookings_appointments.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table
@Entity
public class Bookings{
    @Id
    @GeneratedValue
    private Long id;
    private String bookingName;
    private LocalDate bookingDate;
    private LocalTime bookingStartTime;
    private LocalTime bookingEndTime;
    private String bookingDetails;
    private String bookingStatus;
    private String bookingComments;
    private String employeeAssigned;
    private LocalDateTime createdAt;

    public Bookings(){

    }

    //Actual Constructor
    public Bookings(Long id, String bookingName, LocalDate bookingDate , LocalTime bookingStartTime, LocalTime bookingEndTime, String bookingDetails, String bookingStatus, String bookingComments, String employeeAssigned, LocalDateTime createdAt){
        this.id =  id;
        this.bookingName = bookingName;
        this.bookingDate =  bookingDate;
        this.bookingStartTime = bookingStartTime;
        this.bookingEndTime = bookingEndTime;
        this.bookingDetails = bookingDetails;
        this.bookingStatus = bookingStatus;
        this.bookingComments = bookingComments;
        this.employeeAssigned = employeeAssigned;
        this.createdAt = createdAt;
    }

    //Getters
    public Long getId(){
        return this.id;
    }

    public String getBookingName(){
        return this.bookingName;
    }

    public LocalDate getBookingDate(){
        return this.bookingDate;
    }

    public LocalTime getBookingStartTime(){
        return this.bookingStartTime;
    }

    public LocalTime getBookingEndTime(){
        return this.bookingEndTime;
    }

    public String getBookingDetails(){
        return this.bookingDetails;
    }


    public String getBookingStatus(){
        return this.bookingStatus;
    }

    public String getBookingComments(){
        return this.bookingComments;
    }

    public String getEmployeeAssigned(){
        return this.employeeAssigned;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setBookingName(String bookingName){
        this.bookingName = bookingName;
    }

    public void setBookingDate(LocalDate bookingDate){
        this.bookingDate = bookingDate;
    }

    public void setBookingStartTime(LocalTime bookingStartTime){
        this.bookingStartTime = bookingStartTime;
    }

    public void setBookingEndTime(LocalTime bookingEndTime){
        this.bookingEndTime = bookingEndTime;
    }

    public void setBookingDetails(String bookingDetails){
        this.bookingDetails = bookingDetails;
    }


    public void setBookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }

    public void setBookingComments(String bookingComments){
        this.bookingComments = bookingComments;
    }

    public void setEmployeeAssigned(String employeeAssigned){
        this.employeeAssigned = employeeAssigned;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    //str method
    public String toString(){
        return "ID: " + this.id + '\n' + "Booking Name: " + this.bookingName + '\n' + "Booking Date: " + this.bookingDate + '\n' + "Booking Start Time: " + this.bookingStartTime + '\n' + "Booking End Time: " + this.bookingEndTime + '\n' + "Booking Details: " + this.bookingDetails + '\n' + "Booking Status: " + this.bookingStatus + '\n' + "Booking Comments: " + this.bookingComments + '\n' + "Employee Assigned: " + this.employeeAssigned + '\n' + "Created On: " + this.createdAt;
    }

}