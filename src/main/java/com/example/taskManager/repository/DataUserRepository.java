package com.example.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskManager.entities.DataUser;

public interface DataUserRepository extends JpaRepository<DataUser, Long> {

	// Ejemplos de búsquedas personalizadas:
    DataUser findByEmail(String email);
}
