package com.example.TaskManagement.dto.responseDTO;

import com.example.TaskManagement.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

    String name;

    String username;

    String password;

    Role role;
}
