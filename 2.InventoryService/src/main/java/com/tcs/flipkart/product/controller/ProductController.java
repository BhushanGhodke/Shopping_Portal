package com.tcs.flipkart.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.flipkart.product.binding.FetchProductBinding;
import com.tcs.flipkart.product.binding.ProductBinding;
import com.tcs.flipkart.product.dto.ApiResponse;
import com.tcs.flipkart.product.entity.ImageModel;
import com.tcs.flipkart.product.entity.Product;
import com.tcs.flipkart.product.exception.ProductSaveException;
import com.tcs.flipkart.product.service.ImageModelService;
import com.tcs.flipkart.product.service.ProductService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImageModelService imageModelService;

	@Autowired
	private ObjectMapper mapper;

	
	@PostMapping(value = "/add")
	public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestParam("product") String product,
			@RequestParam("imageFile") MultipartFile file) {

		ApiResponse<Product> response = new ApiResponse<Product>();

		try {
			
			ProductBinding productBinding = mapper.readValue(product, ProductBinding.class);

			ImageModel imageModel = productService.uploadImage(file);
			
			ImageModel saveImage = imageModelService.saveImageUrlToDB(imageModel);
               
			if(saveImage.getImageModelId()!=null) {
				
				productBinding.setImageModelId(saveImage.getImageModelId());

				Product savedProduct = productService.saveProduct(productBinding);
				
				response.setData(savedProduct);
				response.setErrorCode(200);
				response.setMessage("Product Added Successfully");

				return new ResponseEntity<>(response, HttpStatus.OK);
	
			}else {
				response.setData(null);
				response.setErrorCode(500);
				response.setMessage("Something went wrong!!");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			response.setErrorCode(500);
			System.out.println(e.getMessage());

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<FetchProductBinding>> getAllProducts() {

		List<FetchProductBinding> productList = productService.getAllProducts();

		return new ResponseEntity<List<FetchProductBinding>>(productList, HttpStatus.OK);
	}

	@GetMapping("/select/{categoryName}")
	public ResponseEntity<List<FetchProductBinding>> getSelectedProduct(@PathVariable String  categoryName) {

		List<FetchProductBinding> productByCategory = productService.getProductByCategory(categoryName);

		return new ResponseEntity<List<FetchProductBinding>>(productByCategory, HttpStatus.OK);
	}
	
	@GetMapping("getImage/{productId}")
	public ResponseEntity<String> getImageDetailsById(@PathVariable Integer productId){
		String url = productService.getImageUrlById(productId);
	
		return new ResponseEntity<String>(url, HttpStatus.OK);
	}
 
}