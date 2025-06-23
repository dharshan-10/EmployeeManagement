package com.example.EmployeeManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.List;
@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    private String username;
    private String password;
    private String roles;

    public Users(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
