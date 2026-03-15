package com.admin.admine_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queue_management.queue_management.Model.Token;
import com.queue_management.queue_management.Repository.tokenRepo;

import jakarta.transaction.Transactional;

@Service
public class adminService {
    
    @Autowired
    private tokenRepo tokenRepo;
    
    // Serve next token
    @Transactional
    public Token serveNextToken() {
        Token nextToken = tokenRepo.findFirstByStatusOrderByIdAsc("WAITING");
        if (nextToken != null) {
            // Option 1: Mark as COMPLETED and keep in database (for history)
            nextToken.setStatus("COMPLETED");
            return tokenRepo.save(nextToken);  // Save and return
            
            // Option 2: Remove from queue after serving (delete)
            // tokenRepository.delete(nextToken);
            // return nextToken;
        }
        return null;  // No waiting tokens   
    }

    // Delete token by ID
    public boolean deleteToken(Long id) {
        if (tokenRepo.existsById(id)) {
            tokenRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Delete all tokens 
    public void deleteAllTokens() {
        tokenRepo.deleteAll();
    }
}
