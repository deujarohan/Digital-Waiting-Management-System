package com.admin.admine_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.admine_management.Model.Admin;

@Repository
public interface adminRepository extends JpaRepository<Admin, Long>{
    // You can add custom query methods here if needed
    Admin findByEmail(String email);
}
