package com.admin.admine_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.admine_management.Model.Token;

@Repository
public interface tokenRepo extends JpaRepository<Token, Long> {
    // Same method to find the first waiting token
    Token findFirstByStatusOrderByIdAsc(String status);

    // Optional: find by token number
    Token findByTokenNumber(String tokenNumber);
}
