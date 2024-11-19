package com.Booking.Application.system.Repository;

import com.Booking.Application.system.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
     boolean existsById(Long id);
    List<Bus> findBySourceAndDestinationAndDate(String source, String destination, String date);
}
