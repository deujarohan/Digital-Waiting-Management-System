package com.admin.admine_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.admine_management.Model.Admin;
import com.admin.admine_management.Repository.adminRepository;
import com.queue_management.queue_management.Model.Token;
import com.queue_management.queue_management.Repository.tokenRepo;

import jakarta.transaction.Transactional;

@Service
public class adminService {
    
    @Autowired
    private tokenRepo tokenRepo;

    @Autowired
    private adminRepository adminRepo;

    // Get all tokens as a list
    public List<Token> getAllTokens() {
        return tokenRepo.findAll();
    }
    
    // Serve next token
    @Transactional
    public Token serveNextToken() {
        Token nextToken = tokenRepo.findFirstByStatusOrderByIdAsc("WAITING");
        if (nextToken != null) {
            // Option 1: Mark as COMPLETED and keep in database (for history)
            nextToken.setStatus("SERVING");
            return tokenRepo.save(nextToken);  // Save and return
            
            // Option 2: Remove from queue after serving (delete)
            // tokenRepository.delete(nextToken);
            // return nextToken;
        }
        return null;  // No waiting tokens   
    }

    public Token findById(Long id) {
        return tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }

    public void updateStatus(Long id, String status) {
        Token token = tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        token.setStatus(status);
        tokenRepo.save(token);
    }
    
    public void save(Token token) {
        tokenRepo.save(token);
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

    // Register admin
    public Admin register(Admin user) {
        return adminRepo.save(user);
    }

    public void completeToken(Long id) {
        Token token = tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        token.setStatus("COMPLETED");
        tokenRepo.save(token);
    }

}
