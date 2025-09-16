package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Repository.EmployeeRepo;
import com.example.EmployeeManagement.Repository.TaskRepo;
import com.example.EmployeeManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // This returns the username
        }
        return null;
    }

    public List<Task> getTasks(Integer employeeId) {
        List<Task> tasks = new ArrayList<>();
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            optionalEmployee.get().getEmployeeTasks()
                    .stream()
                    .distinct()   // avoid duplicate task IDs
                    .forEach(taskId -> {
                        taskRepo.findById(taskId).ifPresent(tasks::add);
                    });
        }
        return tasks;
    }

    public String getUserRole(String username) {
        return userRepo.findByUsername(username).getRoles();
    }


    public List<Task> getOwnTasks(int EmployeeId) {
        return getTasks(EmployeeId);

    }

    public int getEmployeeIdByUserName(String username) {
        return  employeeRepo.findEmployeeIdByEmployeeName(username);
    }

}
