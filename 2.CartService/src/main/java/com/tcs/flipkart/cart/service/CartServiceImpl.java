package com.tcs.flipkart.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.flipkart.cart.dto.CartDTO;
import com.tcs.flipkart.cart.dto.CartResponse;
import com.tcs.flipkart.cart.entity.Cart;
import com.tcs.flipkart.cart.entity.ImageModel;
import com.tcs.flipkart.cart.entity.Product;
import com.tcs.flipkart.cart.exception.CartEmptyException;
import com.tcs.flipkart.cart.mapper.CartMapper;
import com.tcs.flipkart.cart.repo.CartRepository;
import com.tcs.flipkart.cart.repo.ImageModelRepository;
import com.tcs.flipkart.cart.repo.ProductRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ImageModelRepository imageRepository;
	
	@Override
	public CartDTO addToCart(CartDTO cartDTO) {

		Cart cart = CartMapper.convertToEntity(cartDTO);

		Cart savedCart = cartRepository.save(cart);

		return CartMapper.convertToDto(savedCart);
	}

	@Override
	public List<CartResponse> getCartByUserId(Integer userId) {

		List<Cart> cart = cartRepository.findByUserId(userId);
		System.out.println("getCart By Id Called");
		List<CartResponse> cartResponse = new ArrayList<CartResponse>();

		if (cart.size() >= 0) {
			cart.forEach(x -> {

				Integer productId = x.getProductId();

				Product product = productRepository.findById(productId).get();
				
				ImageModel imageModel = imageRepository.findById(product.getImageModelId()).get();
			
				CartResponse response = new CartResponse();

				response.setProduct(product);
				response.setImageUrl(imageModel.getImageUrl());
				response.setQuantity(x.getQuantity());
				response.setCartId(x.getCartId());
				response.setUserId(x.getUserId());

				cartResponse.add(response);

			});
			return cartResponse;
		}
		throw new CartEmptyException("Cart is Empty");
	}

	@Override
	public CartDTO updateCartQuantityById(CartDTO cartDTO) {
		Optional<Cart> cart = cartRepository.findById(cartDTO.getCartId());

		if (cart.isPresent()) {
			Cart cartData = cart.get();
			cartData.setProductId(cartDTO.getProductId());
			cartData.setQuantity(cartDTO.getQuantity());
			Cart updatedCart = cartRepository.save(cartData);
			return CartMapper.convertToDto(updatedCart);

		} else {
			throw new CartEmptyException("Cart is Empty");
		}

	}

	@Override
	public CartDTO deleteCartById(Integer cartId) {

		Cart cart = cartRepository.findById(cartId).orElseThrow();

		CartDTO cartDTO = CartMapper.convertToDto(cart);
		cartRepository.deleteById(cartId);
		return cartDTO;
	}
}
