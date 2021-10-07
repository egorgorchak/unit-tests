package ru.codinggym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.codinggym.model.User;
import ru.codinggym.model.UserRepository;
import ru.codinggym.service.GreetingsService;

import java.util.Optional;

@RestController
public class HelloController {

    @Autowired
    private GreetingsService greetingsService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return greetingsService.getGreeting();
    }

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return greetingsService.getGreeting(byId.get());
        } else {
            return greetingsService.getGreeting();
        }
    }
}
