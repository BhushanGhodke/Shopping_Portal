package com.tcs.flipkart.cart.service;

import java.util.List;

import com.tcs.flipkart.cart.dto.CartDTO;
import com.tcs.flipkart.cart.dto.CartResponse;

public interface CartService {
 
	public CartDTO addToCart(CartDTO cartDTO);

	public CartDTO updateCartQuantityById(CartDTO cartDTO);

	public List<CartResponse> getCartByUserId(Integer userId);

	public CartDTO deleteCartById(Integer cartId);
		
}
