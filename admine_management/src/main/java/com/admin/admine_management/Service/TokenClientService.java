package com.admin.admine_management.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.admine_management.Model.Token;

@Service
public class TokenClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Token> getTokens() {

        String url = "http://localhost:8080/tokens";

        Token[] tokens = restTemplate.getForObject(url, Token[].class);

        return Arrays.asList(tokens);
    }

}
