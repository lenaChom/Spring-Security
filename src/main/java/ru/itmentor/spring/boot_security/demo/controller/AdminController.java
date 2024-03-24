package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin")
    public String welcome() {
        return "redirect:/admin/all";
    }

    @GetMapping("/admin/all")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.showAllUsers();
        model.addAttribute("users", allUsers);
        return "all-users";}

    @GetMapping ("admin/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.showAllRoles());
        return "user-info";}

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(required=false) String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if(role !=null && role.equals("ROLE_ADMIN")){
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));}
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/all";}

    @RequestMapping("/updateUser")
    public String updateUserForm(@RequestParam("id") Long id, Model model){
        User user = userService.getUser(id);
        Set<Role> roles = user.getRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "editUser";}


    @PostMapping("/editUser")
      public String updateUser(@ModelAttribute("user") User user, @RequestParam(required=false) String role){
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if(role != null && role.equals("ROLE_ADMIN")){
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));}
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/all";}

    @PostMapping ("/deleteUser")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";}
}
