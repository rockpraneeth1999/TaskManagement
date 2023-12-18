package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.requestDTO.TaskRequest;
import com.example.TaskManagement.dto.responseDTO.TaskResponse;

import java.util.List;

public interface TaskService {
    public TaskResponse addTask(TaskRequest taskRequest);
    public List<TaskResponse> getTasks(String email);
    public List<TaskResponse> getAllTasks();
    public TaskResponse updateTask(TaskRequest taskRequest);
    public TaskResponse deleteTask(String email,int taskId);
    public List<TaskResponse> getTasksBySorting(String field,String email,boolean ascending);
    public List<TaskResponse> getTasksBySorting(String field,boolean ascending);
    public List<TaskResponse> getTasksByPagination(int offset,int pageSize);
    public List<TaskResponse> getTasksByPagination(String email,int offset,int pageSize);
    public List<TaskResponse> getTasksByPaginationAndSorting(int offset,int pageSize,String field,boolean ascending);
    public List<TaskResponse> getTasksByPaginationAndSorting(String email,int offset,int pageSize,String field,boolean ascending);
    public List<TaskResponse> getTasksByDate(String date);
    public List<TaskResponse> getTasksByDate(String email,String  date);
    public List<TaskResponse> getTasksByStatus(String status);
    public List<TaskResponse> getTasksByStatus(String email,String status);
}
