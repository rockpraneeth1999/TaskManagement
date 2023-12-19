package com.example.TaskManagement.dto.requestDTO;

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

    String username;

    String password;

    String role;
}
