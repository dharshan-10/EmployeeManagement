package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/getTasks")
    public List<Task> getOwnTasks(@RequestParam int EmployeeId) {
        return employeeService.getOwnTasks(EmployeeId);
    }
    @PostMapping("/updateTask")
    public ResponseEntity<String> updateTask(
            @RequestParam("employeeId") int employeeId,
            @RequestParam("taskId") int taskId,
            @RequestParam("proof") MultipartFile proofFile
    ) {
        String filename = proofFile.getOriginalFilename();
        long size = proofFile.getSize();
    return  taskService.addProof(employeeId, taskId, proofFile);


    }

}
