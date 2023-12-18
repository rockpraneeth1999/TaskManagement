package com.example.TaskManagement.dto.responseDTO;

import com.example.TaskManagement.Enum.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskResponse {

    String title;

    String description;

    LocalDate dueDate;

    Status status;
}
