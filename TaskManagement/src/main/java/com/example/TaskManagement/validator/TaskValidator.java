package com.example.TaskManagement.validator;

import com.example.TaskManagement.Enum.Status;
import com.example.TaskManagement.dto.requestDTO.TaskRequest;
import com.example.TaskManagement.exception.TaskException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TaskValidator {
    public void validateTaskRequest(TaskRequest taskRequest){
        String exceptionMessage="";
        if(taskRequest.getTitle() == null)
            exceptionMessage+="Task title cannot be null\n";
        if(taskRequest.getDescription() == null)
            exceptionMessage+="Task description cannot be null\n";
        if(taskRequest.getDueDate() == null)
            exceptionMessage+="Task due date cannot be null\n";
        if(taskRequest.getStatus() == null)
            exceptionMessage+="Task status cannot be null\n";

        try {
            LocalDate dueDate = LocalDate.parse(taskRequest.getDueDate());
        }
        catch (Exception exception){
            exceptionMessage+="Invalid date format";
        }

        if (!taskRequest.getStatus().equals(Status.COMPLETED.toString()) &&
                !taskRequest.getStatus().equals(Status.IN_PROGRESS.toString()) &&
                !taskRequest.getStatus().equals(Status.PENDING.toString())) {
            exceptionMessage += "Invalid task status";
        }


        if (exceptionMessage.length() !=0)
            throw new TaskException(exceptionMessage);
    }
}
