package com.Booking.Application.system.Controller;


import com.Booking.Application.system.Entity.User;
import com.Booking.Application.system.Service.Impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/viewUser")
    public String view(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "viewUser";
    }

//    @GetMapping("/getUserId/{id}")
//    public String updatePage(@PathVariable("id")Long id, Model model){
//        User user=userService.getById(id);
//        model.addAttribute("user",user);
//        return "UserUpdate";
//    }
//
//    @PostMapping("/save")
//    public String save(@ModelAttribute("user")User user){
//        userService.save(user);
//        return "Success";
//    }
}
