package com.cap.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class SimpleMongoConfig {
	private static Logger log = LoggerFactory.getLogger(SimpleMongoConfig.class);

	private final String applicationName;
	private final String databaseName;
	private final String connectionString;

	public SimpleMongoConfig(@Value("${application.name}") String applicationName,
			@Value("${mongo.database}") String databaseName, @Value("${mongo.connection}") String connectionString) {
		this.applicationName = applicationName;
		this.connectionString = connectionString;
		this.databaseName = databaseName;
	}

	@Bean
	public MongoClient mongo() {
		log.info("Creating MongoClient: applicationName={} connectionString={}", applicationName, connectionString);
		return MongoClients.create(MongoClientSettings.builder().applicationName(applicationName)
				.applyConnectionString(new ConnectionString(connectionString)).build());
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		log.info("Create MongoTemplate: databaseName={}", databaseName);
		return new MongoTemplate(mongo(), databaseName);
	}
}
