package com.example.EmployeeManagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

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
    private List<String> assignedTasks;
}
