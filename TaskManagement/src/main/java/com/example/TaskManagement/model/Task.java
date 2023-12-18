package com.example.TaskManagement.model;

import com.example.TaskManagement.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title",nullable = false)
    String title;

    @Column(name = "description",nullable = false)
    String description;

    @Column(name ="due_date",nullable = false)
    LocalDate dueDate;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
