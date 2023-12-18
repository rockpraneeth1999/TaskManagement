package com.example.TaskManagement.service.Impl;

import com.example.TaskManagement.dto.requestDTO.UserRequest;
import com.example.TaskManagement.dto.responseDTO.UserResponse;
import com.example.TaskManagement.exception.UserException;
import com.example.TaskManagement.model.User;
import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.UserService;
import com.example.TaskManagement.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserResponse addUser(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if(optionalUser.isPresent()){
            throw new UserException("User already exists with "+userRequest.getEmail());
        }

        User user = UserTransformer.userRequestToUser(userRequest);
        User savedUser = userRepository.save(user);

        return UserTransformer.userToUserResponse(savedUser);
    }

    @Override
    public UserResponse deleteUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()){
            throw new UserException("User not found");
        }

        User deleteUser = optionalUser.get();
        userRepository.delete(deleteUser);

        return UserTransformer.userToUserResponse(deleteUser);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            if(userRequest.getName() != null) existingUser.setName(userRequest.getName());
            if(userRequest.getEmail() != null) existingUser.setEmail(userRequest.getEmail());
            if(userRequest.getUsername() != null) existingUser.setUsername(userRequest.getUsername());
            if(userRequest.getPassword() != null) existingUser.setPassword(userRequest.getPassword());
            if(userRequest.getRole() != null) existingUser.setRole(userRequest.getRole());

            User updatedUser = userRepository.save(existingUser);

            return UserTransformer.userToUserResponse(updatedUser);
        }
        else{
            throw new UserException("User not found");
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user : userList){
            userResponseList.add(UserTransformer.userToUserResponse(user));
        }

        return userResponseList;
    }
}
