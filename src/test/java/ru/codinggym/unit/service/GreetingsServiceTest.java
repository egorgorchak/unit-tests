package ru.codinggym.unit.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.codinggym.model.User;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingsServiceTest {

    @Autowired
    private GreetingsService greetingsService;

    @Test
    public void getGreetingTest() {
        String greeting = greetingsService.getGreeting();
        assertTrue(greeting.matches("Hello, Sir! Today is \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}. Have a nice day!"));
    }

    @Test
    public void getGreetingWithUserNameTest() {
        String greeting = greetingsService.getGreeting(getTestUser());
        assertTrue(greeting.matches("Hello, TestTestov! Today is \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}. Have a nice day!"));
    }

    private User getTestUser() {
        return new User(1L, "Test", "Testov", Collections.emptyList());
    }
}