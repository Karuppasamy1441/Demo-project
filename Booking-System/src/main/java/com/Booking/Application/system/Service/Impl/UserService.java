package com.Booking.Application.system.Service.Impl;

import com.Booking.Application.system.Entity.User;

public interface UserService {
    User saveUser(User user);

    User getById(Long id);

    User getUser(String email);

}
