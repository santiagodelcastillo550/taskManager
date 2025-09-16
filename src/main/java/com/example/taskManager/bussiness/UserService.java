package com.example.taskManager.bussiness;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskManager.entities.Task;
import com.example.taskManager.entities.User;
import com.example.taskManager.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

	public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
	
	public boolean authenticate(String name, String password) {
        Optional<User> userOpt = findByName(name);
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}
