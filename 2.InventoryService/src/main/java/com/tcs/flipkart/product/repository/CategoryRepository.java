package com.tcs.flipkart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.flipkart.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
