package com.guvi.busBooking.controller;

import java.util.List;

import com.guvi.busBooking.repository.BusDataRepository;
import com.guvi.busBooking.repository.UserRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guvi.busBooking.model.BusData;
import com.guvi.busBooking.model.User;

@Controller
@RequestMapping("/adminScreen")
@Tag(name = "Admin Controller", description = "Controller for managing bus data and user details")
public class AdminController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BusDataRepository busDataRepository;
	
	
	@ModelAttribute("busDetails")
    public BusData busData() {
        return new BusData();
    }
	
	@GetMapping
	@Operation(summary = "Display Dashboard", description = "Displays the admin dashboard with user details")
    public String displayDashboard(Model model){
		String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "adminScreen";
    }

	private String returnUsername() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User users = userRepository.findByEmail(user.getUsername());
		return users.getName();
	}
	
	@PostMapping
	@Operation(summary = "Save Bus Data", description = "Saves bus data and redirects to the admin screen")
    public String saveBusData(@ModelAttribute("busDetails") BusData busData,Model model){
		String user= returnUsername();
        model.addAttribute("userDetails", user);
        busDataRepository.save(busData);
        model.addAttribute("busDetails", new BusData());
        return "redirect:/adminScreen?success";
    }
	
	@GetMapping("/allRecords")
	@Operation(summary = "Get All Records", description = "Retrieves all bus records and displays them")
	public String getAllRecords(Model model){
		List<BusData> data = busDataRepository.findAll();
		String user= returnUsername();
        model.addAttribute("userDetails", user);
		model.addAttribute("data", data);
		return "allRecords";
	}
	@GetMapping("/delete/{id}")
	@Operation(summary = "Delete Record", description = "Deletes a bus record by ID and redirects to all records")
	public String getDataAfterDelete(@PathVariable int id,Model model){
		busDataRepository.deleteById(id);
		List<BusData> data = busDataRepository.findAll();
		String user= returnUsername();
        model.addAttribute("userDetails", user);
		model.addAttribute("data", data);
		return "redirect:/adminScreen/allRecords?success";
	}
	
}
