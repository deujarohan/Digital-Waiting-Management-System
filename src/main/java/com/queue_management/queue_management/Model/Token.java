package com.queue_management.queue_management.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "service")
    private String service;
    @Column(name = "token_number")
    private String tokenNumber;
    @Column(name = "status")
    private String status; // e.g., PENDING, SERVING, COMPLETED

    public Token() {
    }

    public Token(Long id, String userName, String service, String tokenNumber, String status) {
        this.id = id;
        this.userName = userName;
        this.service = service;
        this.tokenNumber = tokenNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", user=" + userName +
                ", service=" + service +
                ", tokenNumber=" + tokenNumber +
                ", status='" + status + '\'' +
                '}';
    }

}
