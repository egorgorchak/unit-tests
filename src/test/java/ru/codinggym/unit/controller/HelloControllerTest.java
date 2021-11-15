package ru.codinggym.unit.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.codinggym.controllers.HelloController;
import ru.codinggym.repositories.UserRepository;
import ru.codinggym.util.TestUtility;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void hello() {
        assertTrue(helloController.hello().matches("Hello, Sir! Today is \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}. Have a nice day!"));
    }

    @Test
    public void testHello() {
        BDDMockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(TestUtility.getTestUser()));

        assertTrue(helloController.hello(anyLong()).matches("Hello, Test1Testov1! Today is \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}. Have a nice day!"));
    }
}