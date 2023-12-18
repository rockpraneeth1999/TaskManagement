package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dto.requestDTO.UserRequest;
import com.example.TaskManagement.dto.responseDTO.UserResponse;
import com.example.TaskManagement.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;
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

    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUser(@PathVariable("email") String email){
        try {
            UserResponse userResponse = userServiceImpl.deleteUser(email);
            return new ResponseEntity(userResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
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
