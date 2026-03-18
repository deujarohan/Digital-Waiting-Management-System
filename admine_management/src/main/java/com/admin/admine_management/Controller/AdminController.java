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
import org.springframework.web.bind.annotation.RequestParam;
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

    // landing page for postman
    // @GetMapping("/admin/dashboard")
    // public String dashboard() {
    //     return "Welcome to Admin Page"; // You'll create this later
    // }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("tokens", adminService.getAllTokens());
        return "index"; 
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

    // Serve next token for postman
    // @PostMapping("/admin/serve-next")
    // public ResponseEntity<Token> serveNextToken() {
    //     Token token = adminService.serveNextToken();
    //     if (token == null) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.ok(token);
    // }

    // Serve next token
    // Open serve page for a specific token
    @GetMapping("/admin/serve/{id}")
    public String serveTokenPage(@PathVariable Long id, Model model) {
        Token token = adminService.findById(id);
        token.setStatus("SERVING");
        adminService.save(token);
        model.addAttribute("token", token);
        return "serve-token";
    }

    // Update status (pending or completed)
    @PostMapping("/admin/status/{id}")
    public String updateStatus(@PathVariable Long id,
                            @RequestParam String status) {
        adminService.updateStatus(id, status);
        if (status.equals("COMPLETED")) {
            return "redirect:/admin/tokens";
        }
        return "redirect:/admin/serve/" + id;
    }

    // Complete current and serve next in one action
    @PostMapping("/admin/complete-next/{id}")
    public String completeAndNext(@PathVariable Long id,
                                @RequestParam(defaultValue = "COMPLETED") String status) {
        adminService.updateStatus(id, status);
        Token next = adminService.serveNextToken();
        if (next == null) {
            return "redirect:/admin/tokens";
        }
        return "redirect:/admin/serve/" + next.getId();
    }

    // Serve next — redirect to serve page
    @PostMapping("/admin/serve-next")
    public String serveNextToken() {
        Token next = adminService.serveNextToken();
        if (next == null) {
            return "redirect:/admin/tokens";
        }
        return "redirect:/admin/serve/" + next.getId();
    }

    //Postman delete token

    // // Delete token by ID
    // @DeleteMapping("/delete/token/{id}")
    // public ResponseEntity<String> deleteToken(@PathVariable Long id) {
    //     boolean deleted = adminService.deleteToken(id);
    //     if (deleted) {
    //         return ResponseEntity.ok("Token deleted successfully");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                              .body("Token not found");
    //     }
    // }
    // // Delete all tokens
    // @DeleteMapping("/delete/all")
    // public ResponseEntity<String> deleteAllTokens() {
    //     adminService.deleteAllTokens();
    //     return ResponseEntity.ok("All tokens deleted successfully");
    // }

    // Register admin postman
    // @PostMapping("/admin/login")
    // public Admin register(@RequestBody Admin user) {
    //     user.setPassword(encoder.encode(user.getPassword()));
    //     return adminService.register(user);
    // }

    // Delete one token
    @PostMapping("/admin/delete/{id}")
    public String deleteToken(@PathVariable Long id) {
        adminService.deleteToken(id);
        return "redirect:/admin/dashboard";
    }

    // Delete all tokens
    @PostMapping("/admin/delete-all")
    public String deleteAllTokens() {
        adminService.deleteAllTokens();
        return "redirect:/admin/dashboard";
    }

    // Complete current token
    @PostMapping("/admin/complete/{id}")
    public String completeToken(@PathVariable Long id) {
        adminService.completeToken(id);
        return "redirect:/admin/dashboard";
    }

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
