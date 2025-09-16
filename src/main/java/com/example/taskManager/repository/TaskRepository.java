package com.example.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskManager.entities.Status;
import com.example.taskManager.entities.Task;
import com.example.taskManager.entities.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByStatus(Status status);

    List<Task> findByUser(User user);

    List<Task> findByUserAndStatus(User user, Status status); // nuevo
}
