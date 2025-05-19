package com.tcs.flipkart.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

	@GetMapping("/product/getImage/{productId}")
	public ResponseEntity<String> getImageDetailsById(@PathVariable Integer productId);
}
