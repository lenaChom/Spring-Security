package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String helloPage(){
        return "index";
    }

    @GetMapping("/menu")
    public String menuPage(){
        return "menu";
    }

    @RequestMapping  ("/user")
    public String showUser(Model model, Authentication authentication) {
        String auth = authentication.getName();
        User user = userService.getUserByUsername(auth);
        if (user != null) {
        model.addAttribute("user", user);
        } else {return "redirect:/login";}
        return "user";
    }

    @GetMapping("/newUser")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user, @RequestParam(required=false) String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if(role !=null && role.equals("ROLE_ADMIN")){
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));}
        user.setRoles(roles);
        userService.mergeUser(user);
        return "redirect:/user";
    }
}











