package com.Booking.Application.system.Service.Impl;

import com.Booking.Application.system.Entity.Booking;

public interface BookingService {
    void save(Booking booking);

    Booking getById(Long id);
}
