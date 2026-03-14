package com.admin.admine_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
    "com.admin.admine_management", 
    "com.queue_management.queue_management"
})
@EnableJpaRepositories(basePackages = {
    "com.admin.admine_management", 
    "com.queue_management.queue_management"
})
public class AdmineManagementApplication {
	//
	public static void main(String[] args) {
		SpringApplication.run(AdmineManagementApplication.class, args);
	}

}
