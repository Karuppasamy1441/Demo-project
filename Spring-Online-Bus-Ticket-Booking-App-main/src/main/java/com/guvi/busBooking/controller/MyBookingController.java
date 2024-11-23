package com.guvi.busBooking.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.guvi.busBooking.repository.BookingsRepository;
import com.guvi.busBooking.repository.UserRepository;
import com.guvi.busBooking.service.DefaultUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guvi.busBooking.DTO.BookingsDTO;
import com.guvi.busBooking.helper.ObjectCreationHelper;
import com.guvi.busBooking.model.Bookings;
import com.guvi.busBooking.model.User;

@Controller
@RequestMapping("/myBooking")
@Tag(name = "My Booking Controller", description = "Controller for managing user bookings")
public class MyBookingController {

	private DefaultUserService userService;

    public MyBookingController(DefaultUserService userService) {
        super();
        this.userService = userService;
    }
	@Autowired
    BookingsRepository bookingsRepository;
	
	@Autowired
    UserRepository userRepository;
	
	 @ModelAttribute("bookings")
	    public BookingsDTO bookingDto() {
	        return new BookingsDTO();
	    }
	    
		@GetMapping
		@Operation(summary = "View Bookings", description = "Displays the user's bookings")
		public String login(Model model) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
	        UserDetails users = (UserDetails) securityContext.getAuthentication().getPrincipal();
	        User user =userRepository.findByEmail(users.getUsername());
	        List<BookingsDTO> bks = new ArrayList<BookingsDTO>();
			List<Bookings> bs = bookingsRepository.findByUserId(user.getId());
			for (Bookings bookings : bs) {
				BookingsDTO bkks = ObjectCreationHelper.createBookingsDTO(bookings);
				bks.add(bkks);
			}
			model.addAttribute("userDetails", user.getName());
			Collections.reverse(bks);
			model.addAttribute("bookings",bks);
			return "myBookings";
		}
		
		@GetMapping("/generate/{id}")
		@Operation(summary = "Generate Booking", description = "Generates a booking and sends an email")
		public String bookPage(@PathVariable int id,Model model) {
			Optional<Bookings> busdata = bookingsRepository.findById(id);
			 Optional<User> users =userRepository.findById(busdata.get().getUserId());
			String user = users.get().getName();
			BookingsDTO bks = ObjectCreationHelper.createBookingsDTO(busdata.get());
			userService.sendEmail(bks, users.get(),busdata.get().getFileName());
	        model.addAttribute("userDetails", user);
	        List<Bookings> bs = bookingsRepository.findByUserId(users.get().getId());
	        Collections.reverse(bs);
			model.addAttribute("bookings",bs);
			return "redirect:/myBooking?success";	
		}
		
		@GetMapping("/cancel/{id}")
		@Operation(summary = "Cancel Booking", description = "Cancels a booking by ID")
		public String cancelBooking(@PathVariable int id,Model model) {
			Optional<Bookings> busdata = bookingsRepository.findById(id);
			if(busdata.get().isTripStatus()==false) {
				setData(busdata.get(),model);
			return "redirect:/myBooking?alreadyCancel";	
			}else {
				setData(busdata.get(),model);
			busdata.get().setTripStatus(false);
			bookingsRepository.save(busdata.get());
			 
			return "redirect:/myBooking?successCancel";	
			
			}
		}
		
		private void setData(Bookings busData, Model model) {
			Optional<User> users =userRepository.findById(busData.getUserId());
			List<Bookings> bs = bookingsRepository.findByUserId(users.get().getId());
	        Collections.reverse(bs);
			model.addAttribute("bookings",bs);
		}
}
