package com.tcs.flipkart.cart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.flipkart.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	public List<Cart>findByUserId(Integer userId);
}
