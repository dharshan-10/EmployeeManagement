package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

}
