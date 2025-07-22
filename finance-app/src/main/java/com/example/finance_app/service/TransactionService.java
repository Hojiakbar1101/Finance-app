package com.example.finance_app.service;


import com.example.finance_app.model.Transaction;
import com.example.finance_app.model.User;
import com.example.finance_app.repository.TransactionRepository;
import com.example.finance_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Long userId, Double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        user.setBalance(user.getBalance() + amount); 
        userRepository.save(user);
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionsByDateRange(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return transactionRepository.findAllByTimestampBetween(startTime, endTime);
    }

}
