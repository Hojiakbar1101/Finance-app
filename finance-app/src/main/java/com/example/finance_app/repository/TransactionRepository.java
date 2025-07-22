package com.example.finance_app.repository;
import java.util.List;

import com.example.finance_app.model.Transaction;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
        List<Transaction> findByUserId(Long userId);
        List<Transaction> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);

}
