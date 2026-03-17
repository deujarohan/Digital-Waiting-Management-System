package com.queue_management.queue_management.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.queue_management.queue_management.Model.Token;

@Repository
public interface tokenRepo extends JpaRepository<Token, Long>{

    // long countByServiceAndStatus(String service, String status);

     // Same method to find the first waiting token
     Token findFirstByStatusOrderByIdAsc(String status);

     // Optional: find by token number
     Token findByTokenNumber(String tokenNumber);

     //
     List<Token> findByStatusIn(List<String> statuses);
}
