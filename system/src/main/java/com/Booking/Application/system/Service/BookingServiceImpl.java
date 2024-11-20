package com.Booking.Application.system.Service;

import com.Booking.Application.system.Entity.Booking;
import com.Booking.Application.system.Repository.BookingRepository;
import com.Booking.Application.system.Service.Impl.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id).get();
    }
}
