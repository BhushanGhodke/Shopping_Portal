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
		config.put("api_key","948695311846617");
		config.put("api_secret","Xi3b-S2vdGu-eNaHQ7fLJRLPH30");
		config.put("secure", "true");
		return new Cloudinary(config);
	}
	
}
