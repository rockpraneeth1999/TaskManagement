package com.example.TaskManagement.service.Impl;

import com.example.TaskManagement.dto.requestDTO.UserRequest;
import com.example.TaskManagement.dto.responseDTO.UserResponse;
import com.example.TaskManagement.exception.UserException;
import com.example.TaskManagement.model.User;
import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.UserService;
import com.example.TaskManagement.transformer.UserTransformer;
import com.example.TaskManagement.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserValidator userValidator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        userValidator.validateUserRequest(userRequest);

        Optional<User> optionalUser = userRepository.findByUsername(userRequest.getUsername());
        if(optionalUser.isPresent()){
            throw new UserException("User already exists.");
        }

        User user = UserTransformer.userRequestToUser(userRequest);
        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(encryptedPassword);

        User savedUser = userRepository.save(user);

        return UserTransformer.userToUserResponse(savedUser);
    }

    @Override
    public UserResponse deleteUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserException("User not found");
        }

        User deleteUser = optionalUser.get();
        userRepository.delete(deleteUser);

        return UserTransformer.userToUserResponse(deleteUser);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        userValidator.validateUserRequest(userRequest);

        Optional<User> optionalUser = userRepository.findByUsername(userRequest.getUsername());
        if(optionalUser.isPresent()){
            User user = UserTransformer.userRequestToUser(userRequest);
            user.setId(optionalUser.get().getId());

            User updatedUser = userRepository.save(user);

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
