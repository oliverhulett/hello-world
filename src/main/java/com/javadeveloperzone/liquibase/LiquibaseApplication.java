package com.javadeveloperzone.liquibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiquibaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseApplication.class, args);
	}

	@Autowired
	ConfigurableApplicationContext cac;

	@Override
	public void run(String... args) throws Exception {
		cac.close();
	}
}
