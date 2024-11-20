package com.Booking.Application.system.Service;

import com.Booking.Application.system.Entity.Bus;
import com.Booking.Application.system.Repository.BusRepository;
import com.Booking.Application.system.Service.Impl.BusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public Bus getById(Long id) {
        return busRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        busRepository.deleteById(id);
    }

    @Override
    public List<Bus> find( String source,String destination, String date) {
        return busRepository.findBySourceAndDestinationAndDate(source,destination,date);
    }
}
