package com.queue_management.queue_management.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.queue_management.queue_management.Model.Token;

@Component
public interface tokenRepo extends JpaRepository<Token, Long>{

    // long countByServiceAndStatus(String service, String status);
}
