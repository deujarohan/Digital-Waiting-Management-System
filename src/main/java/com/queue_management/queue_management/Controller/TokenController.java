package com.queue_management.queue_management.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TokenController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome to Queue Management System";
    }
    
}
