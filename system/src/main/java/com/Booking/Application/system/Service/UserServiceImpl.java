package com.Booking.Application.system.Service;

import com.Booking.Application.system.Dto.UserDto;
import com.Booking.Application.system.Entity.Role;
import com.Booking.Application.system.Entity.User;
import com.Booking.Application.system.Exception.UserAlreadyExistsException;
import com.Booking.Application.system.Repository.RoleRepository;
import com.Booking.Application.system.Repository.UserRepository;
import com.Booking.Application.system.Service.Impl.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    public User saveUser(UserDto userdto) {
        User user=modelMapper.map(userdto,User.class);
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("EmailId already registered");
        }
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
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

    public Boolean login(String email, String password) {
        try {

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;
        } catch (AuthenticationException ex) {
            System.out.println("Authentication failed: " + ex.getMessage());
            return false;
        }
    }
}
