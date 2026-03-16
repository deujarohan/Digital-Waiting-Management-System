package com.admin.admine_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.queue_management.queue_management.Model.Token;
import com.queue_management.queue_management.Repository.tokenRepo;
import com.admin.admine_management.Model.Admin;
import com.admin.admine_management.Service.TokenClientService;
import com.admin.admine_management.Service.adminService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Component
@Controller
public class AdminController {
    @Autowired
    private TokenClientService tokenClientService;

    @Autowired
    private adminService adminService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    // // Admin page
    // @GetMapping("/admin")
    // public String getAdminPage() {
    //     // return "login";
    // }
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "index";
        // return "Welcome to Admin Page"; // You'll create this later
    }

    // Get all tokens
    // @GetMapping("/admin/tokens")
    // public List<Token> getAllTokens() {
    //     return tokenClientService.getTokens();  // call token-service API
    // }

    @GetMapping("/admin/tokens")
    public List<Token> getAllTokens(Model model) {
        List<Token> tokens = adminService.getAllTokens();
        model.addAttribute("tokens", tokens);
        return tokens;
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
    @DeleteMapping("/delete/token/{id}")
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
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllTokens() {
        adminService.deleteAllTokens();
        return ResponseEntity.ok("All tokens deleted successfully");
    }

    // Register admin postman
    // @PostMapping("/admin/login")
    // public Admin register(@RequestBody Admin user) {
    //     user.setPassword(encoder.encode(user.getPassword()));
    //     return adminService.register(user);
    // }

    // Register admin frontend browser
    @PostMapping("/admin/register")
    // public String register(@RequestBody Admin user) {  //@RequestBody is for JSON APIs
    public String register(@ModelAttribute Admin user) {  //@RequestBody is for JSON APIs
        user.setPassword(encoder.encode(user.getPassword()));
        adminService.register(user);
        return "redirect:/admin/login";
    }

    // Login page
    @GetMapping("/admin/login")
    public String loginPage() {
        return "login";  // Returns login.html from templates folder
    }
}
