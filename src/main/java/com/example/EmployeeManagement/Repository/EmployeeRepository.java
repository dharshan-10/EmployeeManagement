package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
}
