package com.example.finance_app.service;

import com.example.finance_app.model.User;
import com.example.finance_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (user.getBalance() == null) {
            user.setBalance(0.0);
        }
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateBalance(Long userId, Double amount) {
        User user = getUserById(userId);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            userRepository.save(user);
        }
    }
}
