package com.Booking.Application.system.Service.Impl;

import com.Booking.Application.system.Entity.Bus;

import java.util.List;

public interface BusService {
    List<Bus> getAllBuses();

    void saveBus(Bus bus);

    Bus getById(Long id);

    void deleteById(Long id);

    List<Bus> find(String source,String destination,String date);
}
