package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getOwnTasks")
    public List<Task> getOwnTasks(@AuthenticationPrincipal Users users) {
        return employeeService.getOwnTasks();
    }
}
