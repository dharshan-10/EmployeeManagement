package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Repository.EmployeeRepository;
import com.example.EmployeeManagement.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    public ResponseEntity<Void> addTask(Task task, int employeeId) {
        try{
            Task newTask=new Task(task.getTaskName(), task.getTaskDescription(),task.getAssignedDate(),task.getDueDate(),task.isCompletionStatus(),task.isApprovalStatus(),task.getProof());
           Task savedTask= taskRepository.save(newTask);
            Employee employee=employeeRepository.findById(employeeId)
                    .orElseThrow(()-> new RuntimeException("Empoyee Id Not Found"));
            employee.getTaskIds().add(savedTask.getId());
            employeeRepository.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> updateApprovalStatus(int employeeId,String taskId, boolean status) {

    Optional<Task> optionalTask= taskRepository.findById(taskId);
    if(optionalTask.isPresent()){
        Task task= optionalTask.get();
        task.setApprovalStatus(status);
        taskRepository.save(task);
    }
    else {

        return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Void> updateCompletionStatus(String taskId, boolean status) {
        Optional<Task> optionalTask= taskRepository.findById(taskId);
        if(optionalTask.isPresent()){
            Task task= optionalTask.get();
            task.setCompletionStatus(status);
            taskRepository.save(task);
        }
        else {

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
