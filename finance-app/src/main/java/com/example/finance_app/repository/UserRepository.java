package com.example.finance_app.repository;
import com.example.finance_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
