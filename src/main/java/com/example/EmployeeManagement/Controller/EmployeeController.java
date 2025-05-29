package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
    String username=employeeService.getCurrentUsername();
    String role=employeeService.getUserRole(username);
    if(role.equals("admin"))
        employeeService.addEmployee(employee);
        return employee;
    }
}
