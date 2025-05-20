package com.tcs.flipkart.product.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProductConfig {

	@Bean
	public Cloudinary getCloudinary() {
		Map config= new HashMap();		
		config.put("cloud_name","do1glzsfu");
		config.put("api_key","");
		config.put("api_secret","");
		config.put("secure", "true");
		return new Cloudinary(config);
	}
	
}
