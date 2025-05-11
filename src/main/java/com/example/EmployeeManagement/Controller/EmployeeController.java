package com.example.EmployeeManagement.Controller;

<<<<<<< HEAD
import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;
    @GetMapping()
    public List<Employee> getEmployeeDetails(){
return employeeService.getEmployees();
    }
    @GetMapping("/dashboard")
    public List<Task> getEmployeeDetails(@RequestParam int employeeId){
        return employeeService.getEmployeeFullDetails(employeeId);
    }
    @PostMapping("/addProof")
    public ResponseEntity<Void> addProof(@RequestParam int employeeId, @RequestParam String taskId, @RequestParam MultipartFile proof){
        return employeeService.addTaskProof(employeeId, taskId, proof);
    }
    @PostMapping("/updateStatus")
    public ResponseEntity<Void> updateCompletionStatus(@RequestParam String taskId, @RequestParam boolean status){
        return taskService.updateCompletionStatus(taskId, status);
    }

=======
import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);}
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }
    @GetMapping("/")
    public String greet(){
        return "Hello";
    }
>>>>>>> 82bbb83 (Model Changed)
}
