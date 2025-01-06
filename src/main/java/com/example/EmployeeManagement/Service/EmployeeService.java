package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Repository.EmployeeRepository;
import com.example.EmployeeManagement.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaskRepository taskRepository;
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Void> updateApprovalStatus(int employeeId, boolean status) {
        Optional<Employee> employeeOptional=employeeRepository.findById(employeeId);
        if(employeeOptional.isPresent()){
            Employee employee=employeeOptional.get();
            employeeRepository.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> removeEmployee(int employeeId) {
        Optional<Employee> employee= employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            List<String> taskIds = employee.get().getTaskIds();
            for (String taskId : taskIds) {
                taskRepository.deleteById(taskId);
            }

            employeeRepository.deleteById(employeeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public List<Task> getEmployeeFullDetails(int employeeId) {
        Optional<Employee>employee= employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            List<String> TaskIds= employee.get().getTaskIds();
            if (!TaskIds.isEmpty()){
                return taskRepository.findAllById(TaskIds);
            }
        }
            return new ArrayList<>();

    }
@Autowired
private GridFSService gridFSService;
    public ResponseEntity<Void> addTaskProof(int employeeId, String taskId, MultipartFile proof) {
        try{
            String fieldId = gridFSService.uploadProof(proof);
            String proofUrl=gridFSService.getFileUrl(fieldId);
            Optional<Task> optionalTask=taskRepository.findById(taskId);
            if (optionalTask.isPresent()){
                Task task=optionalTask.get();
                task.setProof(proofUrl);
                taskRepository.save(task);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
