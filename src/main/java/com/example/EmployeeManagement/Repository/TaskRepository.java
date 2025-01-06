package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
