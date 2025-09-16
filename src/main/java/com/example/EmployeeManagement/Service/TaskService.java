package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Model.Task;
import com.example.EmployeeManagement.Model.TaskReviewStatus;
import com.example.EmployeeManagement.Repository.TaskRepo;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    public ResponseEntity<String> addProof(int employeeId, int taskId, MultipartFile proofFile) {
        try{
            Task task = taskRepo.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Task not found for the give Id"));
            task.setTaskProof(proofFile.getBytes());
            return new ResponseEntity<>("Added the Proof", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Some Error occurred while uploading the File",
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

    }
    @Autowired
    private EmployeeService employeeService;
        public ResponseEntity<String> updateTaskReviewStatus(TaskReviewStatus taskReviewStatus) {
            Optional<Task> optionalTask=taskRepo.findById(taskReviewStatus.getTaskId());
            if(optionalTask.isPresent()){
                Task task=optionalTask.get();
                task.setTaskFeedback(taskReviewStatus.getTaskFeedback());
                task.setTaskRemarks(taskReviewStatus.getTaskRemarks());
                task.setTaskReviewedByAdmin(taskReviewStatus.isReviewStatus());
                task.setTaskCompletionStatus(taskReviewStatus.isCompletionStatus());
    taskRepo.save(task);
    return ResponseEntity.ok("Updated the review Successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task not found with id " + taskReviewStatus.getTaskId());
        }
    }
