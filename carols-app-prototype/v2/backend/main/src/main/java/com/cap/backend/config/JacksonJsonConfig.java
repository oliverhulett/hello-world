package com.cap.backend.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Configuration
public class JacksonJsonConfig {
	public static void configureMapper(ObjectMapper mapper) {
		mapper.registerModule(new GuavaModule());
		mapper.registerModule(new Jdk8Module());

		mapper.setVisibility(PropertyAccessor.CREATOR,
				JsonAutoDetect.Visibility.PUBLIC_ONLY);
		mapper.setVisibility(PropertyAccessor.GETTER,
				JsonAutoDetect.Visibility.PUBLIC_ONLY);
		mapper.setVisibility(PropertyAccessor.IS_GETTER,
				JsonAutoDetect.Visibility.PUBLIC_ONLY);

		mapper.setTimeZone(TimeZone.getDefault());

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
		mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
				true);
	}

	@Bean
	public ObjectMapper objectMapper(
			Jackson2ObjectMapperBuilder mapperBuilder) {
		ObjectMapper mapper = mapperBuilder.build();

		JacksonJsonConfig.configureMapper(mapper);

		return mapper;
	}
}
