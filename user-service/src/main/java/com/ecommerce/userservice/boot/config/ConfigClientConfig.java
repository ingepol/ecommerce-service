package com.ecommerce.userservice.boot.config;

import org.springframework.cloud.config.client.ConfigClientProperties;

public class ConfigClientConfig {

	private ConfigClientProperties configClientProperties;

	public ConfigClientProperties configClientProperties() {
		this.configClientProperties.setUsername("admin");
		this.configClientProperties.setPassword("admin123");
		return this.configClientProperties;
	}
}
