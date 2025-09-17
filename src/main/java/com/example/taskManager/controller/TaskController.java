package com.example.taskManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskManager.bussiness.TaskService;
import com.example.taskManager.entities.Priority;
import com.example.taskManager.entities.Status;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class TaskController {
	Logger log = LoggerFactory.getLogger(TaskController.class);
	
	private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping
    public String getBoard(Model model, HttpSession session) {
    	log.info("Acceder al board");
    	
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        model.addAttribute("currentPage", "board"); // <-- Indicamos que estamos en board

        // Filtrar tareas por usuario logueado y por estado
        model.addAttribute("pendientes", taskService.getTasksByStatusAndUser(Status.PENDIENTE, username));
        model.addAttribute("enProceso", taskService.getTasksByStatusAndUser(Status.EN_PROCESO, username));
        model.addAttribute("completadas", taskService.getTasksByStatusAndUser(Status.COMPLETADA, username));

        return "board"; // board.html
    }
    
    @PostMapping("/tasks/create")
    public String createTask(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Status status,
                             @RequestParam Priority priority,
                             @RequestParam(required = false) String dueDate,
                             HttpSession session) {

        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login";
        }

        // Llamar al servicio con todos los campos
        taskService.createTask(username, title, description, status, priority, dueDate);

        return "redirect:/board";
    }
    
    @PostMapping("/tasks/update")
    public String updateTask(@RequestParam Long id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Status status,
                             @RequestParam Priority priority,
                             @RequestParam(required = false) String dueDate,
                             HttpSession session) {

        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login";
        }

        taskService.updateTask(id, title, description, status, priority, dueDate);

        return "redirect:/board";
    }
    
    @GetMapping("/calendar")
    public String getCalendarPage(Model model, HttpSession session) {
    	log.info("Acceder al calendario");
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        model.addAttribute("currentPage", "calendar"); // <-- Indicamos que estamos en calendar
        return "calendar";
    }

}
