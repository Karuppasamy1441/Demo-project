package com.Booking.Application.system.Controller;

import com.Booking.Application.system.Entity.Booking;
import com.Booking.Application.system.Entity.Bus;
import com.Booking.Application.system.Repository.BusRepository;
import com.Booking.Application.system.Service.Impl.BookingService;
import com.Booking.Application.system.Service.Impl.BusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BookingController {
    private BookingService bookingService;

    private BusService busService;

    @GetMapping("/bus/{id}")
    public String booking(@PathVariable("id")Long id, Model model){
        Bus bus=busService.getById(id);
        Booking booking=new Booking();
        booking.setBusName(bus.getBusName());
        booking.setDate(bus.getDate());
        booking.setTime(bus.getTime());
        booking.setBus(bus);
        model.addAttribute("booking",booking);
        return "Booking";
    }

    @PostMapping("/book")
    public String booked(@ModelAttribute("booking") Booking booking,Model model) {


        if (booking.getBus() == null || booking.getBus().getId() == null) {
            model.addAttribute("message", "Booking cannot proceed without a valid bus.");
            return "error";
        }

        booking.setTripStatus(true);
        Double price = booking.getBus().getPrice() * booking.getNoOfPersons();
        booking.setTotalCalculated(price);
        bookingService.save(booking);
        model.addAttribute("booking",booking);
        return "pay";
    }

}
