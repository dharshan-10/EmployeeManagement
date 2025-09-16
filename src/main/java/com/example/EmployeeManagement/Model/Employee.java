package com.example.EmployeeManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePhone;
    private String employeeGender;
    private String employeeSalary;
    private String employeeRole;
    private String employeeAddress;
    @ElementCollection
    @CollectionTable(name = "employee_tasks", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "task_id")
    private List<Integer> employeeTasks;

    public Employee(String employeeName, String employeeEmail, String employeePhone, String employeeGender, String employeeSalary, String employeeRole, String employeeAddress, List<Integer> employeeTasks) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
        this.employeeGender = employeeGender;
        this.employeeSalary = employeeSalary;
        this.employeeRole = employeeRole;
        this.employeeAddress = employeeAddress;
        this.employeeTasks = employeeTasks;
    }
}
