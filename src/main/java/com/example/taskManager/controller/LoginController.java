package com.example.taskManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskManager.bussiness.DataUserService;
import com.example.taskManager.bussiness.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	Logger log = LoggerFactory.getLogger(LoginController.class);

	private final UserService userService;
	private final DataUserService dataUserService;

    public LoginController(UserService userService, DataUserService dataUserService) {
        this.userService = userService;
        this.dataUserService = dataUserService;
    }
	
    // ----------------- LOGIN -----------------
    @GetMapping("/login")
    public String login() {
    	log.info("Acceder al login");
    	
        return "login"; // login.html
    }
    
    // Procesar login
    @PostMapping("/login")
    public String doLogin(@RequestParam String name, 
                          @RequestParam String password, 
                          HttpSession session,
                          Model model) {
        log.info("Procesando login para usuario: {}", name);

        if (userService.authenticate(name, password)) {
            session.setAttribute("user", name); // Guardar en sesión
            return "redirect:/board"; // Ir al tablero
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
    
    // ----------------- LOGOUT -----------------
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    // ----------------- REGISTRO -----------------
    @GetMapping("/register")
    public String registro() {
        log.info("Acceder al registro");
        return "register";
    }
    
    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String email,
                             Model model) {
        try {
            dataUserService.registerUser(username, password, name, surname, email);
            log.info("Usuario {} registrado correctamente", username);
            return "redirect:/login"; // Redirige al login después de registrarse
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register"; // Mostrar el formulario con error
        }
    }

}
