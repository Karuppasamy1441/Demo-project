package com.guvi.busBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guvi.busBooking.model.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

}
