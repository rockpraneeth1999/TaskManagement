package com.example.TaskManagement.service.Impl;

import com.example.TaskManagement.Enum.Status;
import com.example.TaskManagement.dto.requestDTO.TaskRequest;
import com.example.TaskManagement.dto.responseDTO.TaskResponse;
import com.example.TaskManagement.exception.TaskException;
import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.model.User;
import com.example.TaskManagement.repository.TaskRepository;
import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.TaskService;
import com.example.TaskManagement.transformer.TaskTransformer;
import com.example.TaskManagement.validator.TaskValidator;
import com.example.TaskManagement.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserValidator userValidator;
    @Override
    public TaskResponse addTask(TaskRequest taskRequest){

        TaskValidator.validateTaskRequest(taskRequest);
        User user = userValidator.validateUser(taskRequest.getEmail());

        Task task = TaskTransformer.TaskRequestToTask(taskRequest);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return TaskTransformer.TaskToTaskresponse(savedTask);
    }

    @Override
    public List<TaskResponse> getTasks(String email) {

        User user = userValidator.validateUser(email);

        List<Task> taskList = taskRepository.findAllByUserEmail(email);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public TaskResponse updateTask(TaskRequest taskRequest) {

        TaskValidator.validateTaskRequest(taskRequest);
        User user = userValidator.validateUser(taskRequest.getEmail());

        Optional<Task> taskOptional = taskRepository.findById(taskRequest.getId());
        Task existingTask = taskOptional.get();

        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setDueDate(LocalDate.parse(taskRequest.getDueDate()));
        existingTask.setStatus(Status.valueOf(taskRequest.getStatus()));

        Task savedTask = taskRepository.save(existingTask);

        return TaskTransformer.TaskToTaskresponse(savedTask);
    }

    @Override
    public TaskResponse deleteTask(String email, int taskId) {

        User user = userValidator.validateUser(email);

        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if(!optionalTask.isPresent()){
            throw new TaskException("Task not found");
        }
        taskRepository.delete(optionalTask.get());

        return TaskTransformer.TaskToTaskresponse(optionalTask.get());
    }

    @Override
    public List<TaskResponse> getTasksBySorting(String field, String email,boolean ascending) {

        User user = userValidator.validateUser(email);
        List<Task> taskList;


            if(ascending)
                taskList = taskRepository.findAllByUserEmail(email,Sort.by(Sort.Direction.ASC,field));
            else
                taskList = taskRepository.findAllByUserEmail(email,Sort.by(Sort.Direction.DESC,field));


        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksBySorting(String field,boolean ascending) {
        List<Task> taskList;
        if(ascending)
            taskList = taskRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        else
            taskList = taskRepository.findAll(Sort.by(Sort.Direction.DESC,field));

        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByPagination(int offset, int pageSize) {
        Page<Task> taskPage = taskRepository.findAll(PageRequest.of(offset,pageSize));
        List<Task> taskList = taskPage.getContent();

        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByPagination(String email, int offset, int pageSize) {

        User user = userValidator.validateUser(email);

        Page<Task> taskPage = taskRepository.findAllByUserEmail(email,PageRequest.of(offset,pageSize));
        List<Task> taskList = taskPage.getContent();

        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByPaginationAndSorting(int offset, int pageSize, String field, boolean ascending) {
        Page<Task> taskPage;
        if(ascending)
            taskPage = taskRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,field));
        else
            taskPage = taskRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.Direction.DESC,field));

        List<Task> taskList = taskPage.getContent();
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByPaginationAndSorting(String email, int offset, int pageSize, String field, boolean ascending) {

        User user = userValidator.validateUser(email);

        Page<Task> taskPage;
        if(ascending)
            taskPage = taskRepository.findAllByUserEmail(email,PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,field));
        else
            taskPage = taskRepository.findAllByUserEmail(email,PageRequest.of(offset,pageSize).withSort(Sort.Direction.DESC,field));

        List<Task> taskList = taskPage.getContent();
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByDate(String date) {
        LocalDate date1;
        try {
            date1 = LocalDate.parse(date);
        }
        catch (Exception exception){
            throw new TaskException("Invalid Date format");
        }
        List<Task> taskList = taskRepository.findAllByDueDate(date1);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByDate(String email, String date) {

        User user = userValidator.validateUser(email);

        LocalDate date1;
        try {
            date1 = LocalDate.parse(date);
        }
        catch (Exception exception){
            throw new TaskException("Invalid Date format");
        }

        List<Task> taskList = taskRepository.findAllByUserEmailAndDueDate(email,date1);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByStatus(String status) {
        Status status1;
        try{
            status1 = Status.valueOf(status);
        }
        catch (Exception exception){
            throw new TaskException("Invalid Status");
        }

        List<Task> taskList = taskRepository.findAllByStatus(status1);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }

    @Override
    public List<TaskResponse> getTasksByStatus(String email, String status) {

        User user = userValidator.validateUser(email);
        Status status1;
        try{
            status1 = Status.valueOf(status);
        }
        catch (Exception exception){
            throw new TaskException("Invalid Status");
        }

        List<Task> taskList = taskRepository.findAllByUserEmailAndStatus(email,status1);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList){
            taskResponseList.add(TaskTransformer.TaskToTaskresponse(task));
        }

        return taskResponseList;
    }
}
