package com.example.taskManager.bussiness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskManager.entities.Priority;
import com.example.taskManager.entities.Status;
import com.example.taskManager.entities.Task;
import com.example.taskManager.entities.User;
import com.example.taskManager.repository.TaskRepository;
import com.example.taskManager.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
    private UserRepository userRepository;

	public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Obtener tareas por estado y por usuario
    public List<Task> getTasksByStatusAndUser(Status status, String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return taskRepository.findByUserAndStatus(user, status);
    }

    // Para compatibilidad, puedes mantener este método si quieres
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }
    
    @Transactional
    public void createTask(String username,
                           String title,
                           String description,
                           Status status,
                           Priority priority,
                           String dueDate) {

        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);

        // Fecha límite
        if (dueDate != null && !dueDate.isEmpty()) {
            task.setLimitDate(LocalDate.parse(dueDate));
        } else {
            task.setLimitDate(LocalDate.now().plusDays(7)); // fecha por defecto
        }

        taskRepository.save(task);
    }
    
    @Transactional
    public void updateTask(Long id,
                           String title,
                           String description,
                           Status status,
                           Priority priority,
                           String dueDate) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));

        existingTask.setTitle(title);
        existingTask.setDescription(description);
        existingTask.setStatus(status);
        existingTask.setPriority(priority);

        if (dueDate != null && !dueDate.isEmpty()) {
            existingTask.setLimitDate(LocalDate.parse(dueDate));
        }

        taskRepository.save(existingTask);
    }
}
