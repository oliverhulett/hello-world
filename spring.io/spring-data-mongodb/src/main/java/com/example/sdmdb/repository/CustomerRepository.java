package com.example.sdmdb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sdmdb.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
}
