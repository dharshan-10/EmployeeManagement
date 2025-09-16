package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.TaskReviewStatus;
import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Repository.EmployeeRepo;
import com.example.EmployeeManagement.Repository.TaskRepo;
import com.example.EmployeeManagement.Repository.UserRepo;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;

    public ResponseEntity<Map<String, Object>> addEmployee(Employee employee) {

        Map<String, Object> response = new HashMap<>();
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
        if ("admin".equals(role)) {
            Users newUser = new Users(employee.getEmployeeName(), employee.getEmployeeName(), employee.getEmployeeRole());
            userService.register(newUser);
            Employee e = new Employee(employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getEmployeePhone(),
                    employee.getEmployeeGender(), employee.getEmployeeSalary(), employee.getEmployeeRole(),
                    employee.getEmployeeAddress(), employee.getEmployeeTasks());
            employeeRepo.save(e);
            response.put("success", true);
            response.put("message", "Added the Employee Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("success", false);
            response.put("message", "Unauthorized: Only admin can add employees.");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
              }

    }

    @Autowired
    private TaskRepo taskRepo;
    public ResponseEntity<String> addTask(Integer employeeId, Task task) {
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
        if ("admin".equalsIgnoreCase(role.trim())) {
            Task newTask = taskRepo.save(task);

            Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.getEmployeeTasks().add(newTask.getTaskId());
                employeeRepo.save(employee);

                return new ResponseEntity<>("Task assigned to employee successfully.", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Employee not found.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Unauthorized: Only admin can add tasks.", HttpStatus.FORBIDDEN);
        }
    }

    public List<Employee> getAllEmployees() {
        System.out.println("Inside getEmployees");
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
        if ("admin".equals(role)) {
            List<Employee> optionalEmployee= employeeRepo.findAll();
            List<Employee> employees=new ArrayList<>();
            for(Employee employee:optionalEmployee){
                if (!employee.getEmployeeRole().equals("admin")){
                    employees.add(employee);
                }
            }
            return employees;
        }
        return new ArrayList<>();
    }

@Autowired
private TaskService taskService;
    public ResponseEntity<String> updateTaskReviewStatus(TaskReviewStatus taskReviewStatus) {
        return taskService.updateTaskReviewStatus(taskReviewStatus);
    }
}
