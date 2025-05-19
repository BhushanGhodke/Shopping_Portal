package com.tcs.flipkart.cart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.flipkart.cart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
