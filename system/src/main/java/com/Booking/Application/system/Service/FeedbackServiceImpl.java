package com.Booking.Application.system.Service;

import com.Booking.Application.system.Entity.Feedback;
import com.Booking.Application.system.Repository.FeedbackRepository;
import com.Booking.Application.system.Service.Impl.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
