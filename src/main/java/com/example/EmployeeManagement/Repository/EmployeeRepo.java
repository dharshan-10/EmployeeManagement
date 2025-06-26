package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query("SELECT e.employeeRole FROM Employee e WHERE e.employeeName = :employeeName")
    String findEmployeeRoleByEmployeeName(@Param("employeeName") String employeeName);

    @Query("SELECT e.employeeId FROM Employee e WHERE e.employeeName = :employeeName")
    int findEmployeeIdByEmployeeName(@Param("employeeName") String employeeName);

}
