package com.demo.ApplicationRunnerTutorial;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunnerTutorialApplication /* implements CommandLineRunner */
		implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRunnerTutorialApplication.class, args);
	}

	
	  public void run(ApplicationArguments args0) {
	  System.err.println("application runner class"); }
	 

	/*
	 * @Override public void run(String... arg0) throws Exception {
	 * System.out.println("Hello world from Command Line Runner"); }
	 */
}
