package com.tcs.flipkart.cart.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tcs.flipkart.cart.dto.CartDTO;
import com.tcs.flipkart.cart.entity.Cart;

@Component
public class CartMapper {
	
	private static final ModelMapper mapper = new ModelMapper();

	public static CartDTO convertToDto(Cart cart) {
		return mapper.map(cart, CartDTO.class);
	}

	public static Cart convertToEntity(CartDTO cartDTO) {
		return mapper.map(cartDTO, Cart.class);
	}

}
