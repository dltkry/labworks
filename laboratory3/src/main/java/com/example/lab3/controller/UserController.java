package com.example.lab3.controller;

import com.example.lab3.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(idCounter++);
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
