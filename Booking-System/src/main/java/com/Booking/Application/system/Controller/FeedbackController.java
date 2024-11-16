package com.Booking.Application.system.Controller;

import com.Booking.Application.system.Entity.Feedback;
import com.Booking.Application.system.Service.Impl.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class FeedbackController {
    private FeedbackService feedbackService;

    @GetMapping("/feedback")
    public String feedbackForm(Model model){
        model.addAttribute("feedback",new Feedback());
        return "FeedbackForm";
    }

    @PostMapping("/saveFeedback")
    public  String toSaveFeedback(@ModelAttribute("feedback")Feedback feedback){
        feedbackService.saveFeedback(feedback);
        return "SuccessFeedback";
    }
}
