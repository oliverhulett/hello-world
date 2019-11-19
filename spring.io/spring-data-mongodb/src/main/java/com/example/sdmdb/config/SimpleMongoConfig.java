package com.example.sdmdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class SimpleMongoConfig {

	@Bean
	public MongoClient mongo() {
		return MongoClients.create(MongoClientSettings.builder().applicationName("hello-world::spring-data-mongodb")
				.applyConnectionString(new ConnectionString("mongodb://localhost:27017")).build());
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "test");
	}
}
