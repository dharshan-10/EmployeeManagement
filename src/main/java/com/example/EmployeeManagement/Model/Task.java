package com.example.EmployeeManagement.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Document(collection = "task")
public class Task {
    @Id
    private String id;
    private String taskName;
    private String taskDescription;

    public Task(String taskName, String taskDescription, LocalDate assignedDate, LocalDate dueDate, boolean completionStatus, boolean approvalStatus, String proof) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.completionStatus = completionStatus;
        this.approvalStatus = approvalStatus;
        this.proof = proof;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    private LocalDate assignedDate;
    private LocalDate dueDate;
    private boolean completionStatus;
    private boolean approvalStatus;
    private String proof;
}
