package com.admin.admine_management.Model;

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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;        // Display name (your app needs this)
    
    @Column(unique = true, nullable = false)
    private String email;       // Used as username for login
    
    private String phoneNumber; // Contact info (your app needs this)
    
    // 🔐 SECURITY FIELDS (MISSING IN YOUR VERSION)
    
    @Column(nullable = false)
    private String password;    // CRITICAL - Encrypted password
    
    @Column(nullable = false)
    private String role;        // CRITICAL - "ADMIN" or "FRONT_OFFICER"
}
