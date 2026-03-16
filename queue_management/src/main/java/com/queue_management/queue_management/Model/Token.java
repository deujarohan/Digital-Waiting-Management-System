package com.queue_management.queue_management.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;
    @Column(name = "service")
    @JsonProperty("service")
    private String service;
    @Column(name = "token_number", unique = true)
    @JsonProperty("token_number")
    private String tokenNumber;
    @Column(name = "status")
    @JsonProperty("status")
    private String status; // e.g., PENDING, SERVING, COMPLETED

    // public void setStatus(String status) {
    //     this.status = status;
    // }

}
