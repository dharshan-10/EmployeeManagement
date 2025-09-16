package com.example.EmployeeManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity

@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String adminName;
    private String adminEmail;
    private String adminPhone;
    private String adminGender;
    private String adminSalary;
    private String adminRole;
    private String adminAddress;
    @ElementCollection
    @CollectionTable(name = "admin_tasks", joinColumns = @JoinColumn(name = "admin_id"))
    @Column(name = "task")
    private List<String> assignedTasks;
}
