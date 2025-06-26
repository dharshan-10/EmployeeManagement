package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Service.AdminService;
import com.example.EmployeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        return adminService.addEmployee(employee);
    }

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return adminService.getAllEmployees();
    }

    @PostMapping("/addTask")
    public ResponseEntity<String> addTask(@RequestParam Integer EmployeeId, @RequestBody Task task) {
        return adminService.addTask(EmployeeId, task);
    }

    @GetMapping("/getTasks")
    public List<Task> getTasks(@RequestParam Integer EmployeeId) {
        return employeeService.getTasks(EmployeeId);
    }
}
