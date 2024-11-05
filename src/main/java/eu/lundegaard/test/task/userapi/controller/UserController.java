package eu.lundegaard.test.task.userapi.controller;

import eu.lundegaard.test.task.userapi.entity.Address;
import eu.lundegaard.test.task.userapi.entity.User;
import eu.lundegaard.test.task.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * File is created by andreychernenko at 02.11.2024
 * 
 * 
 */


    
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    };

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/newAddress")
    public ResponseEntity<Address> newUserAddress(@PathVariable Long userId, @RequestBody Address address) {
        return ResponseEntity.ok(userService.saveNewAddress(userId, address));
    }

    @PutMapping("/{userId}/edit")
    public ResponseEntity<User> editUser(@PathVariable Long userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @PutMapping("/{userId}/editAddress/{addressId}")
    public ResponseEntity<Address> editUserAddress(@PathVariable Long userId, @PathVariable Long addressId, @RequestBody Address address) {
        return ResponseEntity.ok(userService.updateUserAddress(userId, addressId, address));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/deleteAddress/{addressId}")
    public ResponseEntity deleteUserAddress(@PathVariable Long userId, @PathVariable Long addressId) {
        userService.deleteUserAddress(userId, addressId);
        return ResponseEntity.noContent().build();
    }

}
