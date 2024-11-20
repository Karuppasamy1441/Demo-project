package com.Booking.Application.system.Controller;

import com.Booking.Application.system.Dto.FindDto;
import com.Booking.Application.system.Dto.UserDto;
import com.Booking.Application.system.Entity.Bus;
import com.Booking.Application.system.Entity.User;
import com.Booking.Application.system.Exception.UserAlreadyExistsException;
import com.Booking.Application.system.Repository.UserRepository;
import com.Booking.Application.system.Service.Impl.BusService;
import com.Booking.Application.system.Service.Impl.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class LoginController {
    private BusService busService;
    @Autowired
    private UserService userService;

    private UserRepository userRepository;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(@RequestParam String email,@RequestParam String password,Model model,HttpSession session) {


        try{
            boolean Success = UserService.login(email, password);
            if (Success) {
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
        }
        catch (AuthenticationException e) {
            session.setAttribute("msg", e.getMessage());
            return "redirect:/login";
        }

    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/userRegister")
    public String saveUser(@ModelAttribute("user") UserDto user, HttpSession session) {
        try {
            User userInfo = userService.saveUser(user);

            if (userInfo != null) {
                session.setAttribute("msg", "Register successfully");
            } else {
                session.setAttribute("msg", "Something wrong server");
            }
        } catch (UserAlreadyExistsException e) {
            session.setAttribute("msg", e.getMessage());
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model){
//        String user=returnUsername();
//        model.addAttribute("userDetail",user);
        model.addAttribute("findDto",new FindDto());
        return "home";
    }

    @PostMapping("find")
    public String findBus(@ModelAttribute("findDto")FindDto findDto,Model model){
        List<Bus> option=busService.find(findDto.getSource(),findDto.getDestination(),findDto.getDate());
        model.addAttribute("findBuses",option);
        return "home";
    }

//    private String returnUsername() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        UserDetails email= (UserDetails) securityContext.getAuthentication().getPrincipal();
//        User users = userService.getUser(user.getEmail());
//        return users.getEmail();
//    }
}
