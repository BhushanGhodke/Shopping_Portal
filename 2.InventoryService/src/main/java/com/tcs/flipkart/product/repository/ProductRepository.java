package com.tcs.flipkart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.flipkart.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
