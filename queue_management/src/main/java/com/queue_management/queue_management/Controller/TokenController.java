package com.queue_management.queue_management.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.queue_management.queue_management.Model.Token;
import com.queue_management.queue_management.Service.tokenService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;


@Controller
public class TokenController {

    @Autowired
    public tokenService service;

        @GetMapping("/")
        public String index() {
            return "landing";
        }

        @GetMapping("/mytoken")
        public String welcome(Model model, HttpSession session) {
            // return "Welcome to Queue Management System";
            // get the token that was just issued to this customer from session
            Long tokenId = (Long) session.getAttribute("tokenId");

            if (tokenId == null) {
                return "redirect:/create";
            }

            // customer's own token
            Token token = service.findById(tokenId);

            // everyone in the queue with status PENDING or SERVING
            List<Token> queueList = service.findByStatusIn(List.of("WAITING", "PENDING", "SERVING"));

            model.addAttribute("token", token);
            model.addAttribute("queueList", queueList);
            return "queue";
        }

    //create token postman
    // @PostMapping("/create")
    // public Token createToken(@Valid @RequestBody Token token) {
    //     return service.createToken(token);
    // }

    //create token form browser
    @GetMapping("/create")
    public String showQueueForm(Model model) {
        model.addAttribute("token", new Token());
        return "index";
    }

    @PostMapping("/create")
    public String createToken(@Valid @ModelAttribute("token") Token token, Model model, BindingResult result,
    HttpSession session) {
        if (result.hasErrors()) {
            return "index";
        }
        Token saved = service.createToken(token);
        session.setAttribute("tokenId", saved.getId()); // save to session
        return "redirect:/mytoken";
    }

    @GetMapping("/tokens")
    public List<Token> getAllTokens(Model model) {
        List<Token> tokens = service.getAllTokens();
        model.addAttribute("tokens", tokens);
        return tokens;
    }
    
    
}
