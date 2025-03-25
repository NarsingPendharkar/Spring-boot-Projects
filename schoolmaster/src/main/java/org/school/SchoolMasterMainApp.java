package org.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SchoolMasterMainApp {
	public static void main(String[] args) {
		SpringApplication.run(SchoolMasterMainApp.class, args);
	}
}
