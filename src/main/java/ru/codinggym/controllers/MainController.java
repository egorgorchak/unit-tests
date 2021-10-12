package ru.codinggym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.codinggym.model.User;
import ru.codinggym.service.CurrencyService;
import ru.codinggym.service.GreetingsService;
import ru.codinggym.service.UserService;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private GreetingsService greetingsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/hello")
    public String hello() {
        return greetingsService.getGreeting();
    }

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable Long id) {
        Optional<User> byId = userService.getUserByID(id);
        if (byId.isPresent()) {
            return greetingsService.getGreeting(byId.get());
        } else {
            return greetingsService.getGreeting();
        }
    }

    @GetMapping("/exchange")
    public String currency(@RequestParam String currency) {
        return currencyService.getLatestCurrency(currency);
    }
}
