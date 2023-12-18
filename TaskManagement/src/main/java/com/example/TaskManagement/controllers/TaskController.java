package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dto.requestDTO.TaskRequest;
import com.example.TaskManagement.dto.responseDTO.TaskResponse;
import com.example.TaskManagement.service.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskServiceImpl taskServiceImpl;
    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody TaskRequest taskRequest){
        try{
            TaskResponse taskResponse = taskServiceImpl.addTask(taskRequest);
            return new ResponseEntity(taskResponse, HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-tasks/{email}")
    public ResponseEntity getTasks(@PathVariable("email") String email){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasks(email);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-tasks")
    public ResponseEntity getAllTasks(){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getAllTasks();
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateTask(@RequestBody TaskRequest taskRequest){
        try {
            TaskResponse taskResponse = taskServiceImpl.updateTask(taskRequest);
            return new ResponseEntity(taskResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{email}/{id}")
    public ResponseEntity deleteTask(@PathVariable("email") String email,@PathVariable("id") int taskId){
        try {
            TaskResponse taskResponse = taskServiceImpl.deleteTask(email,taskId);
            return new ResponseEntity(taskResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sort/{field}/{email}/{ascending}")
    public ResponseEntity sortTasks(@PathVariable("field") String field,@PathVariable("email") String email,@PathVariable("ascending") boolean ascending){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksBySorting(field,email,ascending);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/sort/{field}/{ascending}")
    public ResponseEntity sortTasks(@PathVariable("field") String field,@PathVariable("ascending") boolean ascending){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksBySorting(field,ascending);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity getTasksByPagination(@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByPagination(offset,pageSize);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pagination/{email}/{offset}/{pageSize}")
    public ResponseEntity getTasksByPagination(@PathVariable("email") String email,@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByPagination(email,offset,pageSize);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/pagination-sorting/{offset}/{field}/{pageSize}/{ascending}")
    public ResponseEntity getTasksByPaginationAndSorting(@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize,@PathVariable("field") String field,@PathVariable("ascending") boolean ascending){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByPaginationAndSorting(offset,pageSize,field,ascending);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pagination-sorting/{email}/{offset}/{field}/{pageSize}/{ascending}")
    public ResponseEntity getTasksByPaginationAndSorting(@PathVariable("email") String email,@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize,@PathVariable("field") String field,@PathVariable("ascending") boolean ascending){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByPaginationAndSorting(email,offset,pageSize,field,ascending);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/task-by-date/{date}")
    public ResponseEntity getTasksByDate(@PathVariable("date") String date){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByDate(date);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/task-by-date/{email}/{date}")
    public ResponseEntity getTasksByDate(@PathVariable("email") String email,@PathVariable("date") String date){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByDate(email,date);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/task-by-status/{status}")
    public ResponseEntity getTasksByStatus(@PathVariable("status") String status){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByStatus(status);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/task-by-status/{email}/{status}")
    public ResponseEntity getTasksByStatus(@PathVariable("email") String email,@PathVariable("status") String status){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByStatus(email,status);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
