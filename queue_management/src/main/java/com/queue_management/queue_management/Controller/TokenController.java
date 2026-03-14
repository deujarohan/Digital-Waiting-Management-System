package com.queue_management.queue_management.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.queue_management.queue_management.Model.Token;
import com.queue_management.queue_management.Service.tokenService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TokenController {

    @Autowired
    public tokenService service;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Queue Management System";
    }

    @PostMapping("/create")
    public Token createToken(@Valid @RequestBody Token token) {
        return service.createToken(token);
    }

    @GetMapping("/tokens")
    public List<Token> getAllTokens(Model model) {
        List<Token> tokens = service.getAllTokens();
        model.addAttribute("tokens", tokens);
        return tokens;
    }
    
    
}
