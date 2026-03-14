package com.admin.admine_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.admine_management.Model.Token;
import com.admin.admine_management.Repository.tokenRepo;

@Service
public class adminService {
    
    @Autowired
    private tokenRepo tokenRepo;
    
    // Serve next token
    public Token serveNextToken() {
        Token nextToken = tokenRepo.findFirstByStatusOrderByIdAsc("WAITING");
        if (nextToken != null) {
            // nextToken.setStatus("PENDING");
            nextToken.setStatus("COMPLETED"); // or SERVING first, then COMPLETED after work
            tokenRepo.save(nextToken);
            tokenRepo.delete(nextToken);
        }
        return nextToken;   
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
