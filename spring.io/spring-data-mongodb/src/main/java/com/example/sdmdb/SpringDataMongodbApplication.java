package com.example.sdmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.sdmdb.model.Customer;
import com.example.sdmdb.model.ImmutableCustomer;
import com.example.sdmdb.repository.CustomerRepository;

@SpringBootApplication
public class SpringDataMongodbApplication implements CommandLineRunner {

	@Autowired
	ConfigurableApplicationContext cac;

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(ImmutableCustomer.builder().firstName("Alice").lastName("Smith").build());
		repository.save(ImmutableCustomer.builder().firstName("Bob").lastName("Smith").build());

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

		cac.close();
	}
}
