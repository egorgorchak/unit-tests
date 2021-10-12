package ru.codinggym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.codinggym.model.User;
import ru.codinggym.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsersFromDB() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserByID(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
