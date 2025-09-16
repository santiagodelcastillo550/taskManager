package com.example.taskManager.bussiness;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskManager.entities.DataUser;
import com.example.taskManager.entities.Rol;
import com.example.taskManager.entities.User;
import com.example.taskManager.repository.UserRepository;

@Service
public class DataUserService {

	private final UserRepository userRepository;

    public DataUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(String username, String password, String name, String surname, String email) {
        if (userRepository.existsById(username)) {
            throw new IllegalArgumentException("El usuario ya existe: " + username);
        }

        // 1. Creamos User (login)
        User user = new User(username, password, Rol.USER);

        // 2. Creamos DataUser (perfil)
        DataUser dataUser = new DataUser(name, surname, email);

        // 3. Vinculamos
        user.setDataUser(dataUser);

        // 4. Guardamos (cascade = ALL se encarga del DataUser)
        return userRepository.save(user);
    }
}
