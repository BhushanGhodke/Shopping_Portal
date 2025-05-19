package com.tcs.flipkart.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.flipkart.product.dto.ApiResponse;
import com.tcs.flipkart.product.entity.Category;
import com.tcs.flipkart.product.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<String>> createProductCategory(@RequestBody Category category){
		
		boolean status = categoryService.addCategory(category);
	
		ApiResponse<String> response= new ApiResponse<String>();
		if(status) {
			response.setErrorCode(201);
			response.setMessage("Category Created Successfully");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setErrorCode(500);
			response.setMessage("Category Creation Failed");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<ApiResponse<List<Category>>> getAllCategory(){
		List<Category> allCategory = categoryService.getAllCategory();
	
		ApiResponse<List<Category>> categoryList= new ApiResponse<List<Category>>();
 		categoryList.setData(allCategory);
 		categoryList.setErrorCode(200);
 		categoryList.setMessage("Category List Fetched");
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
}
