package com.example.TaskManagement.model;

import com.example.TaskManagement.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "username",unique = true,nullable = false)
    String username;

    @Column(name = "password",nullable = false)
    String password;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Task> books = new ArrayList<>();
}
