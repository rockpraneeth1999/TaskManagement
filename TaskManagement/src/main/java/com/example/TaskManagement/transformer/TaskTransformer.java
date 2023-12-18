package com.example.TaskManagement.transformer;

import com.example.TaskManagement.Enum.Status;
import com.example.TaskManagement.dto.requestDTO.TaskRequest;
import com.example.TaskManagement.dto.responseDTO.TaskResponse;
import com.example.TaskManagement.model.Task;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TaskTransformer {

    public Task TaskRequestToTask(TaskRequest taskRequest){
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(LocalDate.parse(taskRequest.getDueDate()))
                .status(Status.valueOf(taskRequest.getStatus()))
                .build();
    }

    public TaskResponse TaskToTaskresponse(Task task){
        return TaskResponse.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .build();
    }
}
