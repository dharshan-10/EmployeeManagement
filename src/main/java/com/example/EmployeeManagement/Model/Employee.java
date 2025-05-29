package com.example.EmployeeManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "task")
    private List<String> employeeTasks;


}
