package com.Booking.Application.system.Repository;
import com.Booking.Application.system.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,Long> {

}
