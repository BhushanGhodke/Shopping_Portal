package com.tcs.flipkart.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tcs.flipkart.order.dto.ApiResponse;
import com.tcs.flipkart.order.dto.CartDTO;

@FeignClient(name = "cart-service")
public interface CartServiceClient {
	
	@DeleteMapping("/cart/delete/{cartId}")
	public ApiResponse<CartDTO> deleteCartById(@PathVariable Integer cartId);

}
