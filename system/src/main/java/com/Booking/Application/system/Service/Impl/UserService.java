package com.Booking.Application.system.Service.Impl;

import com.Booking.Application.system.Dto.LoginDto;
import com.Booking.Application.system.Dto.UserDto;
import com.Booking.Application.system.Entity.User;

public interface UserService {
    User saveUser(UserDto user);

    User getById(Long id);

    User getUser(String email);

    static Boolean login(String email, String password) {
        return null;
    }
}
