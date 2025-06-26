package com.example.EmployeeManagement.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    public Task(String taskName, String taskDescription, String taskAssignedTo, LocalDate taskAssignedDate,
                LocalDate taskDueDate, byte[] taskProof, String taskRemarks, String taskFeedback) {

        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskAssignedTo = taskAssignedTo;

        this.taskAssignedDate = taskAssignedDate;
        this.taskDueDate = taskDueDate;
        this.taskProof = taskProof;
        this.taskRemarks = taskRemarks;
        this.taskFeedback = taskFeedback;
        this.taskReviewedByAdmin = false;
        this.taskCompletionStatus = false;
        this.taskProgress = 0;
    }

    private String taskName;
    private String taskDescription;
    private String taskAssignedTo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskAssignedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDueDate;
    @Lob
    private byte[] taskProof;
    private String taskRemarks;
    private String taskFeedback;
    private boolean taskReviewedByAdmin = false;
    private boolean taskCompletionStatus = false;
    private int taskProgress = 0;
}
