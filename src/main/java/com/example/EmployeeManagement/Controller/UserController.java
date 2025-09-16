package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
    public Map<String, Object> getCurrentUser() {
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
        System.out.println(username);
        int employeeId = employeeService.getEmployeeIdByUserName(username); // fetch from DB/service

        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("role", role);
        response.put("employeeId", employeeId);

        return response; // Spring automatically converts it to JSON
    }



}
