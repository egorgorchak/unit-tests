package ru.codinggym.service;

import org.springframework.stereotype.Service;
import ru.codinggym.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GreetingsService {

    public String getGreeting() {
        return "Hello, Sir! Today is " + getCurrentDate() + ". Have a nice day!";
    }

    public String getGreeting(User user) {
        String userName = user.getFirstName() + user.getLastName();
        return "Hello, " + userName + "! Today is " + getCurrentDate() + ". Have a nice day!";
    }

    private String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }
}
