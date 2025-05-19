package com.tcs.flipkart.product.service;

import java.util.List;

import com.tcs.flipkart.product.entity.Category;

public interface CategoryService {

	public boolean addCategory(Category category);
	
	
	public List<Category> getAllCategory();
}
