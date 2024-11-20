package com.Booking.Application.system.Repository;

import com.Booking.Application.system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.function.Supplier;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String username);

    Optional<User> findByName(String name);
}
