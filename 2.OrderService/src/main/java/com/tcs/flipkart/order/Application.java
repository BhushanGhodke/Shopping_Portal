package com.tcs.flipkart.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.tcs.flipkart.order.service.OrderService;

@SpringBootApplication
@EnableFeignClients
public class Application implements CommandLineRunner {

	@Autowired
	private OrderService orderService;
	
	@Override
	public void run(String... args) throws Exception {
		 
		orderService.updateOrderStatus();
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
