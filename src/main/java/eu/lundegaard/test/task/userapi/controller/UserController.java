package eu.lundegaard.test.task.userapi.controller;

import eu.lundegaard.test.task.userapi.entity.User;
import eu.lundegaard.test.task.userapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * File is created by andreychernenko at 02.11.2024
 * 
 * 
 */


    
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    };

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
