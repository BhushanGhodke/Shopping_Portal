package com.tcs.flipkart.cart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.flipkart.cart.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
