package com.example.TaskManagement.dto.requestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskRequest {

    int id;

    String title;

    String description;

    String dueDate;

    String status;

    String email;
}
