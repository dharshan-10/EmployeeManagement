package com.example.EmployeeManagement.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "employee_table")
public class Employee {
    @Id
    private int id;  // Changed to camelCase

    private String employeeName;  // Changed to camelCase
    private String employeePhone;  // Changed to camelCase
    private String employeeGender;  // Changed to camelCase
    private String employeeSalary;  // Changed to camelCase
    private String employeeRole;  // Changed to camelCase
private List<String> taskIds= new ArrayList<>();
    // No-argument constructor required by Spring Data MongoDB
    public Employee() {
    }

    // Custom constructor
    public Employee(int id, String employeeName, String employeePhone, String employeeGender, String employeeSalary, String employeeRole, String taskId) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeGender = employeeGender;
        this.employeeSalary = employeeSalary;
        this.employeeRole = employeeRole;
        this.taskIds.add(taskId);
    }

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
