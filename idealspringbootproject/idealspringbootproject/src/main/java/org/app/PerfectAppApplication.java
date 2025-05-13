package org.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PerfectAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfectAppApplication.class, args);
	}

}
