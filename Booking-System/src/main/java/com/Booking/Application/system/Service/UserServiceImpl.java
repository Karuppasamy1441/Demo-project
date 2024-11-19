package com.Booking.Application.system.Service;

import com.Booking.Application.system.Entity.User;
import com.Booking.Application.system.Repository.UserRepository;
import com.Booking.Application.system.Service.Impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User getById(Long id) {
        return null;
    }
}
