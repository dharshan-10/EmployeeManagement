package com.example.EmployeeManagement.Model;

import lombok.Data;

@Data
public class TaskReviewStatus {
        private int employeeId;
        private int taskId;
        private  String taskRemarks;
        private  String taskFeedback;
        private boolean reviewStatus;
        private  boolean completionStatus;
            }


