package com.guvi.busBooking.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guvi.busBooking.model.Bookings;




public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

	List<Bookings> findByUserId(int userId);
	
}
