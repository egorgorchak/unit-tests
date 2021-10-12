package ru.codinggym.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.codinggym.model.User;
import ru.codinggym.repositories.UserRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>((List<User>) userRepository.findAll(), HttpStatus.OK);
    }
}
