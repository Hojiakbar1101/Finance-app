package com.example.finance_app.Controller;


import com.example.finance_app.model.Transaction;
import com.example.finance_app.model.User;
import com.example.finance_app.service.TransactionService;
import com.example.finance_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{userId}/transactions")
    public Transaction createTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        return transactionService.createTransaction(userId, transaction.getAmount());
    }

    @GetMapping("/{userId}/transactions")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactionsByDateRange(@RequestParam String start, @RequestParam String end) {
        return transactionService.getTransactionsByDateRange(start, end);
    }
}
