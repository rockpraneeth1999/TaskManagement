package com.example.TaskManagement.validator;

import com.example.TaskManagement.exception.UserException;
import com.example.TaskManagement.model.User;
import com.example.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {
    @Autowired
    UserRepository userRepository;
    public User validateUser(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()){
            throw new UserException("User not found");
        }

        return optionalUser.get();
    }
}
