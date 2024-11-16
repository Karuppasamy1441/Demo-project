package com.Booking.Application.system.Controller;

import com.Booking.Application.system.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/pay")
    public String processPayment(@RequestParam("email") String email, Model model) {
        emailService.sendEmail("gkaruppasamy2207@gmail.com","Booking","Success");
        emailService.sendEmail(email, "Payment Confirmation", "Your payment was successful!");

        model.addAttribute("message", "Payment successful! Confirmation email sent.");
        return "confirmation";
    }
}
