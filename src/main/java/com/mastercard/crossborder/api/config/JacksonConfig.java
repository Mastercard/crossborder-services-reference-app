package com.mastercard.crossborder.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mastercard.crossborder.api.rest.response.Details;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Details.class, new DetailsDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}
}
