package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByUsername(String email);
}
