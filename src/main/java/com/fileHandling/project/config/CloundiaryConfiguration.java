package com.fileHandling.project.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloundiaryConfiguration {

	@Value("${cloudinary.api_key}")
	private String api_key;
	
	@Value("${cloudinary.api_secret}")
	private String api_secret;
	
	@Value("${cloudinary.cloud_name}")
	private String clond_name;
	
	
	@Bean
	public Cloudinary getCloudinary() {
		HashMap<String, String> config = new HashMap();
		config.put("api_key", api_key);
		config.put("api_secret", api_secret);
		config.put("cloud_name", clond_name);
		
		return new Cloudinary(config);
	}
}

