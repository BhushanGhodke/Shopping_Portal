package com.tcs.flipkart.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.flipkart.cart.dto.ApiResponse;
import com.tcs.flipkart.cart.dto.CartDTO;
import com.tcs.flipkart.cart.dto.CartResponse;
import com.tcs.flipkart.cart.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
	
	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ApiResponse<CartDTO> addProductToCart(@RequestBody CartDTO cartDTO){
	CartDTO cartdto = cartService.addToCart(cartDTO);	
	ApiResponse<CartDTO> response = new ApiResponse<CartDTO>();
	if(cartdto!=null) {
		response.setData(cartdto);
		response.setErrorCode(200);
		response.setMessage("Product Added Successfully");
		return response;
	}
	else {
		
		response.setData(null);
		response.setErrorCode(500);
		response.setMessage("Something Went Wrong !!");
	    return response;
	}
	}

	@GetMapping("/getById/{userId}")
	public ApiResponse<List<CartResponse>> getCartByUserId(@PathVariable Integer userId){
		 List<CartResponse> cartResponse = cartService.getCartByUserId(userId);
		ApiResponse<List<CartResponse>> response = new ApiResponse<List<CartResponse>>();
		if(cartResponse!=null) {
			response.setData(cartResponse);
			response.setErrorCode(200);
			response.setMessage("Cart Retrieved Successfully");
			return response;
		}
		else {
			
			response.setData(null);
			response.setErrorCode(500);
			response.setMessage("Something Went Wrong !!");
		    return response;
		}
		
	}
	
	
	@PutMapping("/update")
	public ApiResponse<CartDTO> updateCart(@RequestBody CartDTO cartDTO){
		
		CartDTO cartdto = cartService.updateCartQuantityById(cartDTO);
		ApiResponse<CartDTO> response = new ApiResponse<CartDTO>();
		if(cartdto!=null) {
			response.setData(cartdto);
			response.setErrorCode(200);
			response.setMessage("Cart Updated Successfully");
			return response;
		}
		else {
			
			response.setData(null);
			response.setErrorCode(500);
			response.setMessage("Something Went Wrong !!");
		    return response;
		}
	}
	
	@DeleteMapping("/delete/{cartId}")
	public ApiResponse<CartDTO> deleteCartById(@PathVariable Integer cartId){
		CartDTO cartdto = cartService.deleteCartById(cartId);
		ApiResponse<CartDTO> response = new ApiResponse<CartDTO>();
		if(cartdto!=null) {
			response.setData(cartdto);
			response.setErrorCode(200);
			
			response.setMessage("Cart Deleted Successfully");
			return response;
		}
		else {
			response.setData(null);
			response.setErrorCode(500);
			response.setMessage("Something Went Wrong !!");
		    return response;
		}
		
	}
}
