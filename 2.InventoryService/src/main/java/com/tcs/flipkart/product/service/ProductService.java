package com.tcs.flipkart.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tcs.flipkart.product.binding.FetchProductBinding;
import com.tcs.flipkart.product.binding.ProductBinding;
import com.tcs.flipkart.product.dto.OrderDetails;
import com.tcs.flipkart.product.entity.ImageModel;
import com.tcs.flipkart.product.entity.Product;

public interface ProductService {

	public Product saveProduct(ProductBinding productBinding);
	
	public List<FetchProductBinding> getAllProducts();
	
	public ImageModel uploadImage(MultipartFile file);
	
	public List<FetchProductBinding> getProductByCategory(Integer categoryId);
	
	public String getImageUrlById(Integer productId);
	
}
