package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {
}
