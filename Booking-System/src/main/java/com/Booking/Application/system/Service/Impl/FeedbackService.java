package com.Booking.Application.system.Service.Impl;

import com.Booking.Application.system.Entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    void saveFeedback(Feedback feedback);
}
