package com.example.EmployeeManagement.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Collection;

@Document(collection = "admin")
public class Admin {
    public Admin() {
    }

    public Admin(int id, String task, String description,  LocalDate dueDate,  String proof) {
        Id = id;
        Task = task;
        Description = description;
        AssignedDate = LocalDate.now();
        DueDate = dueDate;
        CompletionStatus = false;
        ApprovalStatus = false;
        Proof = proof;
    }

    @Id
    private int Id;
    private String Task;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getAssignedDate() {
        return AssignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        AssignedDate = assignedDate;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    public boolean isCompletionStatus() {
        return CompletionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        CompletionStatus = completionStatus;
    }

    public boolean isApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        ApprovalStatus = approvalStatus;
    }

    public String getProof() {
        return Proof;
    }

    public void setProof(String proof) {
        Proof = proof;
    }

    private String Description;
    private LocalDate AssignedDate;
    private LocalDate DueDate;
    private boolean CompletionStatus;
    private boolean ApprovalStatus;
    private String Proof;
}
