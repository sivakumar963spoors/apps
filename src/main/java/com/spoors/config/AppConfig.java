package com.spoors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
	
	private String disableInternFieldNamesForJsonObjectMapper;

	public String getDisableInternFieldNamesForJsonObjectMapper() {
		return disableInternFieldNamesForJsonObjectMapper;
	}

	public void setDisableInternFieldNamesForJsonObjectMapper(String disableInternFieldNamesForJsonObjectMapper) {
		this.disableInternFieldNamesForJsonObjectMapper = disableInternFieldNamesForJsonObjectMapper;
	}
	
	

}
