package com.queue_management.queue_management.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queue_management.queue_management.Model.Token;
import com.queue_management.queue_management.Repository.tokenRepo;

@Service
public class tokenService {
    @Autowired
    private tokenRepo repo;

    // Get all tokens as a list
    public List<Token> getAllTokens() {
        return repo.findAll();
    }

    // Create a new token
    public Token createToken(Token token) {
        // Get existing tokens list
        long count = repo.count();

        // Generate token number (e.g., B-1, B-2)
        // String tokenNumber = service.getPrefix() + "-" + (todayCount + 1);
        token.setTokenNumber(String.valueOf(count + 1));
        if (token.getService() == null) token.setService("Deposite");
        if (token.getStatus() == null) token.setStatus("WAITING");
        return repo.save(token);
    }

    // Find token by ID
    public Token findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
    // Find token by status
    public List<Token> findByStatusIn(List<String> statuses) {
        return repo.findByStatusIn(statuses);
    }

}
