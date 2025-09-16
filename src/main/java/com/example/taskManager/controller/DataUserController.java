package com.example.taskManager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskManager.entities.DataUser;
import com.example.taskManager.repository.DataUserRepository;

@RestController
@RequestMapping("/datauser")
public class DataUserController {

	private final DataUserRepository dataUserRepository;

    public DataUserController(DataUserRepository dataUserRepository) {
        this.dataUserRepository = dataUserRepository;
    }

    // 1. Obtener todos los DataUser
    @GetMapping
    public List<DataUser> getAllDataUsers() {
        return dataUserRepository.findAll();
    }

    // 2. Obtener un DataUser por ID
    @GetMapping("/{id}")
    public ResponseEntity<DataUser> getDataUserById(@PathVariable Long id) {
        return dataUserRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Actualizar un DataUser
    @PutMapping("/{id}")
    public ResponseEntity<DataUser> updateDataUser(
            @PathVariable Long id,
            @RequestBody DataUser updatedDataUser) {

        return dataUserRepository.findById(id).map(dataUser -> {
            dataUser.setName(updatedDataUser.getName());
            dataUser.setSurname(updatedDataUser.getSurname());
            dataUser.setEmail(updatedDataUser.getEmail());
            dataUserRepository.save(dataUser);
            return ResponseEntity.ok(dataUser);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 4. Eliminar un DataUser
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDataUser(@PathVariable Long id) {
        return dataUserRepository.findById(id).map(dataUser -> {
            dataUserRepository.delete(dataUser);
            return ResponseEntity.ok("DataUser eliminado correctamente");
        }).orElse(ResponseEntity.notFound().build());
    }
}
