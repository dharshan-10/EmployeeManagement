package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }

    @GetMapping("/")
    public String get() {
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
        System.out.println(role);
        return "UserName " + username + " Role " + role;
    }

}
