package com.queue_management.queue_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queue_management.queue_management.Repository.tokenRepo;

@Service
public class tokenService {
    @Autowired
    private tokenRepo repo;

}
