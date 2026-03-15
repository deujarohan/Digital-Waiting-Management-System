package com.admin.admine_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.admin.admine_management.Model.Admin;
import com.admin.admine_management.Repository.adminRepository;

@Service
public class AdminUserDetailService implements UserDetailsService{
    @Autowired
    private adminRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = repo.findByEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User.builder()
            .username(admin.getEmail())
            .password(admin.getPassword())
            .roles(admin.getRole())
            .build();
    }

}
