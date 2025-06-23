package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.Users;
import com.example.EmployeeManagement.Repository.EmployeeRepo;
import com.example.EmployeeManagement.Repository.TaskRepo;
import com.example.EmployeeManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired 
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    public ResponseEntity<String> addEmployee(Employee employee) {

        String username=employeeService.getCurrentUsername();
        String role=employeeService.getUserRole(username);
        if ("admin".equals(role)) {
            Users newUser=new Users(employee.getEmployeeEmail(),employee.getEmployeeName(),employee.getEmployeeRole());
            userService.register(newUser);
            Employee e= new Employee(employee.getEmployeeName(),employee.getEmployeeEmail(),employee.getEmployeePhone(),
                    employee.getEmployeeGender(),employee.getEmployeeSalary(),employee.getEmployeeRole(),
                    employee.getEmployeeAddress(),employee.getEmployeeTasks());
            employeeRepo.save(e);
            return new ResponseEntity<>("Employee added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Unauthorized: Only admin can add employees.", HttpStatus.FORBIDDEN);
        }
        
    }
@Autowired
private TaskRepo taskRepo;
    public ResponseEntity<String> addTask(Integer employeeId, Task task) {
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
    System.out.println("The role is"+role);
        if ("admin".equals(role)) {
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
        String username = employeeService.getCurrentUsername();
        String role = employeeService.getUserRole(username);
        if("admin".equals(role)){
            return employeeRepo.findAll();
        }
        return new ArrayList<>();
    }

    public List<Task> getTasks(Integer employeeId) {
        List<Task> tasks= new ArrayList<>();
        Optional<Employee> optionalEmployee= employeeRepo.findById(employeeId);
        if (optionalEmployee.isPresent()){
            for(int taskId:optionalEmployee.get().getEmployeeTasks()){
                Optional<Task> optionalTask = taskRepo.findById(taskId);
                optionalTask.ifPresent(tasks::add);
            }
        }
        return tasks;
    }
}
