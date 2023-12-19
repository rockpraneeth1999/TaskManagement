package com.example.TaskManagement.transformer;

import com.example.TaskManagement.Enum.Role;
import com.example.TaskManagement.dto.requestDTO.UserRequest;
import com.example.TaskManagement.dto.responseDTO.UserResponse;
import com.example.TaskManagement.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransformer {

    public User userRequestToUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .role(Role.valueOf(userRequest.getRole()))
                .build();
    }

    public UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
