package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dto.requestDTO.UserRequest;
import com.example.TaskManagement.dto.responseDTO.UserResponse;
import com.example.TaskManagement.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @PreAuthorize("permitAll()")
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){
        try {
            UserResponse userResponse = userServiceImpl.addUser(userRequest);
            return new ResponseEntity(userResponse,HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username){
        try {
            UserResponse userResponse = userServiceImpl.deleteUser(username);
            return new ResponseEntity(userResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserRequest userRequest){
        try {
            UserResponse userResponse = userServiceImpl.updateUser(userRequest);
            return new ResponseEntity(userResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-users")
    public ResponseEntity getAllUsers(){
        try {
            List<UserResponse> userResponseList = userServiceImpl.getAllUsers();
            return new ResponseEntity(userResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
