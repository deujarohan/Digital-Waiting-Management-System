package com.admin.admine_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.admine_management.Model.Token;
import com.admin.admine_management.Service.TokenClientService;
import com.admin.admine_management.Service.adminService;

@Component
@RestController
public class AdminController {
    @Autowired
    private TokenClientService tokenClientService;

    @Autowired
    private adminService adminService;

    // Admin page
    @GetMapping("/admin")
    public String getAdminPage() {
        return "This is Admin page.";
    }

    // Get all tokens
    @GetMapping("/admin/tokens")
    public List<Token> getAllTokens() {
        return tokenClientService.getTokens();  // call token-service API
    }

    // Serve next token
    @PostMapping("/admin/serve-next")
    public ResponseEntity<Token> serveNextToken() {
        Token token = adminService.serveNextToken();
        if (token == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(token);
    }

    // Delete token by ID
    @DeleteMapping("/token/{id}")
    public ResponseEntity<String> deleteToken(@PathVariable Long id) {
        boolean deleted = adminService.deleteToken(id);
        if (deleted) {
            return ResponseEntity.ok("Token deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Token not found");
        }
    }

    // Delete all tokens
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllTokens() {
        adminService.deleteAllTokens();
        return ResponseEntity.ok("All tokens deleted successfully");
    }
}
