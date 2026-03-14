package com.admin.admine_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.admin.admine_management.Model.Admin;

@Component
public interface adminRepository extends JpaRepository<Admin, Long>{

}
