package eu.lundegaard.test.task.userapi.service;

import eu.lundegaard.test.task.userapi.entity.User;
import eu.lundegaard.test.task.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * File is created by andreychernenko at 02.11.2024
 */

@Service
public class UserService {

    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
