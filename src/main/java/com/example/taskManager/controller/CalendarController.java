package com.example.taskManager.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskManager.entities.Task;
import com.example.taskManager.entities.User;
import com.example.taskManager.repository.TaskRepository;
import com.example.taskManager.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class CalendarController {

	private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CalendarController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/tasks")
    public List<Map<String, Object>> getUserTasks(HttpSession session) {
        String username = (String) session.getAttribute("user");

        List<Map<String, Object>> events = new ArrayList<>();

        if (username == null) {
            // usuario no logueado → devolver lista vacía
            return events;
        }

        userRepository.findByName(username).ifPresent(user -> {
            List<Task> tasks = taskRepository.findByUser(user);

            for (Task task : tasks) {
                Map<String, Object> event = new HashMap<>();
                event.put("title", task.getTitle());

                // Aseguramos formato ISO
                if (task.getLimitDate() != null) {
                    // si es LocalDate → YYYY-MM-DD
                    event.put("start", task.getLimitDate().toString());
                } else {
                    // fecha nula → ignorar o usar fecha de hoy
                    event.put("start", java.time.LocalDate.now().toString());
                }

                event.put("id", task.getId());
                events.add(event);
            }
        });

        return events;
    }

}
