package com.guvi.busBooking.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.guvi.busBooking.DTO.BookingsDTO;
import com.guvi.busBooking.DTO.UserRegisteredDTO;
import com.guvi.busBooking.model.Bookings;
import com.guvi.busBooking.model.User;

public interface DefaultUserService extends UserDetailsService{

	User save(UserRegisteredDTO userRegisteredDTO);

	Bookings updateBookings(BookingsDTO bookingDTO,UserDetails user);
	
	void sendEmail(BookingsDTO bookingDTO, User users, String nameGenrator);


	
}
