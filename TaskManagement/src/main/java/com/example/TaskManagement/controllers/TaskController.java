package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dto.requestDTO.TaskRequest;
import com.example.TaskManagement.dto.responseDTO.TaskResponse;
import com.example.TaskManagement.service.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskServiceImpl taskServiceImpl;
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity deleteTask(@PathVariable("username") String username,@PathVariable("id") int taskId){
        try {
            TaskResponse taskResponse = taskServiceImpl.deleteTask(username,taskId);
            return new ResponseEntity(taskResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/sort/{field}/{username}/{ascending}")
    public ResponseEntity sortTasks(@PathVariable("field") String field,@PathVariable("username") String username,@PathVariable("ascending") boolean ascending){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksBySorting(field,username,ascending);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/pagination/{username}/{offset}/{pageSize}")
    public ResponseEntity getTasksByPagination(@PathVariable("username") String username,@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByPagination(username,offset,pageSize);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/pagination-sorting/{username}/{offset}/{field}/{pageSize}/{ascending}")
    public ResponseEntity getTasksByPaginationAndSorting(@PathVariable("username") String username,@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize,@PathVariable("field") String field,@PathVariable("ascending") boolean ascending){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByPaginationAndSorting(username,offset,pageSize,field,ascending);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/task-by-date/{username}/{date}")
    public ResponseEntity getTasksByDate(@PathVariable("username") String username,@PathVariable("date") String date){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByDate(username,date);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/task-by-status/{username}/{status}")
    public ResponseEntity getTasksByStatus(@PathVariable("username") String username,@PathVariable("status") String status){
        try {
            List<TaskResponse> taskResponseList = taskServiceImpl.getTasksByStatus(username,status);
            return new ResponseEntity(taskResponseList,HttpStatus.FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
