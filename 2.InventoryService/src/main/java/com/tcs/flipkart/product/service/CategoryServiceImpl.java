package com.tcs.flipkart.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.flipkart.product.entity.Category;
import com.tcs.flipkart.product.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public boolean addCategory(Category category) {
	
		Category savedCategory = categoryRepository.save(category);
		
		return savedCategory.getCategoryId()!=null;
	}
	
	@Override
	public List<Category> getAllCategory() {
	
		return categoryRepository.findAll();
	}

}
