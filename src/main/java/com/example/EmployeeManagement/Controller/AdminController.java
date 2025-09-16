package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.TaskReviewStatus;
import com.example.EmployeeManagement.Service.AdminService;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) {
        return adminService.addEmployee(employee);
    }

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return adminService.getAllEmployees();
    }
    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody TaskReviewStatus taskReviewStatus){
            return adminService.updateTaskReviewStatus(taskReviewStatus);
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
