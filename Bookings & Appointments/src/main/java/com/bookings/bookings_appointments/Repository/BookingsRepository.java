package com.bookings.bookings_appointments.Repository;

import com.bookings.bookings_appointments.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

}
