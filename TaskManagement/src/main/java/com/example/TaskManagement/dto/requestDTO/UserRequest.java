package com.example.TaskManagement.dto.requestDTO;

import com.example.TaskManagement.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

    String name;

    String email;

    String username;

    String password;

    Role role;
}
