package ru.codinggym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.codinggym.model.User;
import ru.codinggym.model.UserRepository;
import ru.codinggym.service.CurrencyService;
import ru.codinggym.service.GreetingsService;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private GreetingsService greetingsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrencyService currencyService;

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

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @GetMapping("/exchange")
    public String currency(@RequestParam String currency) {
        return currencyService.getLatestCurrency(currency);
    }
}
