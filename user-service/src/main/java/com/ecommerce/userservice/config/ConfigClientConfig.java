package com.ecommerce.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClientConfig {

	@Autowired
	private ConfigClientProperties configClientProperties;

	@Bean
	public ConfigClientProperties configClientProperties() {
        this.configClientProperties.setUsername("admin");
        this.configClientProperties.setPassword("admin123");
		return this.configClientProperties;
	}
}