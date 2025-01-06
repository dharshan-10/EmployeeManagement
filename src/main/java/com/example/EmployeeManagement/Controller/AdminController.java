package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    TaskService taskService;
    @GetMapping("")
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }
    @GetMapping("/employeeDetails")
    public List<Task> getEmployeeDetails(@RequestParam int employeeId){
        return employeeService.getEmployeeFullDetails(employeeId);
    }
    @PostMapping("/addemployee")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    @PostMapping("/addtask")
        public ResponseEntity<Void> addTask(@RequestBody Task task, @RequestParam int employeeId){
            return taskService.addTask(task, employeeId);

    }
    @DeleteMapping("/removeemployee")
    public ResponseEntity<Void> removeEmployee(@RequestParam int employeeId){
        return employeeService.removeEmployee(employeeId);
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateApprovalStatus(@RequestParam int employeeId,@RequestParam String taskId, @RequestParam boolean status){
        return taskService.updateApprovalStatus(employeeId,taskId, status);
    }
}
