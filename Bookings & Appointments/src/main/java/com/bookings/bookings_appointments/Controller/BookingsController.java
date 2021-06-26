package com.bookings.bookings_appointments.Controller;

import com.bookings.bookings_appointments.Entity.Bookings;
import com.bookings.bookings_appointments.Exceptions.BookingsException;
import com.bookings.bookings_appointments.Repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/api/bookings/booking")
@RestController
public class BookingsController {
    @Autowired
    private BookingsRepository myBookingsRepository;

    @GetMapping("/list")
    public List<Bookings> listBookings(){
        return myBookingsRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Bookings getBookingById(@PathVariable Long id) throws BookingsException{
        return myBookingsRepository.findById(id).orElseThrow(() -> new BookingsException("Error Finding Appointment!"));
    }

    @PostMapping("/new")
    public Bookings createNewBooking(@RequestBody Bookings myBookings){
        return myBookingsRepository.save(myBookings);
    }

    @PatchMapping("/update/{id}")
    public Bookings updateBooking(@RequestBody Bookings myBookings, @PathVariable Long id){
        return myBookingsRepository.findById(id).map((bookings) ->{
            bookings.setBookingName(myBookings.getBookingName());
            bookings.setBookingDate(myBookings.getBookingDate());
            bookings.setBookingStartTime(myBookings.getBookingStartTime());
            bookings.setBookingEndTime(myBookings.getBookingEndTime());
            bookings.setBookingDetails(myBookings.getBookingDetails());
            bookings.setBookingStatus(myBookings.getBookingStatus());
            bookings.setBookingComments(myBookings.getBookingComments());
            bookings.setEmployeeAssigned(myBookings.getEmployeeAssigned());
            bookings.setCreatedAt(myBookings.getCreatedAt());
            return myBookingsRepository.save(bookings);
        }).orElseGet(() ->{
            myBookings.setId(id);
            return myBookingsRepository.save(myBookings);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookingById(@PathVariable Long id){
        myBookingsRepository.deleteById(id);
    }

    @GetMapping("/count")
    public long getBookingsCount(){
        return myBookingsRepository.count();
    }


}
