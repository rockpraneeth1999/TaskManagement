package com.example.TaskManagement.validator;

import com.example.TaskManagement.Enum.Role;
import com.example.TaskManagement.dto.requestDTO.UserRequest;
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
    public User validateUser(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UserException("User not found");
        }

        return optionalUser.get();
    }

    public void validateUserRequest(UserRequest userRequest){
        String message="";

        if(userRequest.getName() == null)
            message+="Name cannot be null\n";
        if(userRequest.getUsername() == null)
            message+="Username cannot be null\n";
        if(userRequest.getPassword() == null)
            message+="Password cannot be null\n";
        if(userRequest.getRole() == null)
            message+="Role cannot be null\n";

        String role = userRequest.getRole();
        if(!role.equals(Role.USER.toString()) && !role.equals(Role.ADMIN.toString()))
            message+="Invalid Status";

        if(message.length() != 0)
            throw new UserException(message);
    }
}
