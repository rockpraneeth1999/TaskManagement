package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.requestDTO.UserRequest;
import com.example.TaskManagement.dto.responseDTO.UserResponse;

import java.util.List;

public interface UserService {
    public UserResponse addUser(UserRequest userRequest);

    public UserResponse deleteUser(String email);

    public UserResponse updateUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();
}
