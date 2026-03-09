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
        String tokenNumber = String.valueOf(count + 1);

        Token tokenObj = new Token();
        tokenObj.setTokenNumber(tokenNumber);
        tokenObj.setService("Deposite");
        tokenObj.setStatus("WAITING");
        // tokenObj.setCreatedAt(LocalDateTime.now());

        return repo.save(tokenObj);
    }

}
