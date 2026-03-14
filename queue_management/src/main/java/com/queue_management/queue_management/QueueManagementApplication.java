package com.queue_management.queue_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.queue_management.queue_management.Model")
@EnableJpaRepositories("com.queue_management.queue_management.Repository")  // This tells Spring where to find repositories
public class QueueManagementApplication {
	//
	public static void main(String[] args) {
		SpringApplication.run(QueueManagementApplication.class, args);
	}

}
