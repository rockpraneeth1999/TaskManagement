package com.example.TaskManagement.repository;

import com.example.TaskManagement.Enum.Status;
import com.example.TaskManagement.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    public List<Task> findAllByUserUsername(String username);
    public List<Task> findAllByUserUsername(String email, Sort sort);
    public Page<Task> findAllByUserUsername(String email, PageRequest pageRequest);
    List<Task> findAllByDueDate(LocalDate date);
    List<Task> findAllByUserUsernameAndDueDate(String userEmail,LocalDate dueDate);
    List<Task> findAllByStatus(Status status);
    List<Task> findAllByUserUsernameAndStatus(String email,Status status);;
}
