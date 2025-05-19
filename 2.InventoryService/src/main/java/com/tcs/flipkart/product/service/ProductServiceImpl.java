package com.tcs.flipkart.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageReader;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.tcs.flipkart.product.binding.FetchProductBinding;
import com.tcs.flipkart.product.binding.ProductBinding;
import com.tcs.flipkart.product.dto.OrderDetails;
import com.tcs.flipkart.product.entity.ImageModel;
import com.tcs.flipkart.product.entity.Product;
import com.tcs.flipkart.product.exception.ProductNotFoundException;
import com.tcs.flipkart.product.repository.ImageModelRepository;
import com.tcs.flipkart.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Cloudinary cloudinary;

	@Autowired
	private ImageModelRepository imageModelRepository;

	@Override
	public Product saveProduct(ProductBinding productBinding) {

		Product p = new Product();

		BeanUtils.copyProperties(productBinding, p);

		Product product = productRepository.save(p);

		return product;

	}

	@Override
	public List<FetchProductBinding> getAllProducts() {

		List<Product> productList = productRepository.findAll();

		List<FetchProductBinding> productFetch = new ArrayList<>();

		productList.forEach(x -> {

			FetchProductBinding binding = new FetchProductBinding();

			ImageModel imageModel = imageModelRepository.findById(x.getImageModelId()).get();
			String imageUrl = imageModel.getImageUrl();

			binding.setImageUrls(imageUrl);

			BeanUtils.copyProperties(x, binding);

			productFetch.add(binding);

		});

		return productFetch;
	}

	@Override
	public ImageModel uploadImage(MultipartFile multipartFile) {

		String imageUrl = null;

		ImageModel imageModel = new ImageModel();

		try {

			Map data = cloudinary.uploader().upload(multipartFile.getBytes(), Map.of());

			imageUrl = (String) data.get("url");

			imageModel.setImageUrl(imageUrl);

			return imageModel;

		} catch (Exception e) {
			throw new RuntimeException("Image Uploading Failed");
		}

	}

	@Override
	public List<FetchProductBinding> getProductByCategory(Integer categoryId) {

		List<Product> productList = productRepository.findAll();

		List<Product> collect = productList.stream()

				.filter(product -> product.getCategoryId() == categoryId).collect(Collectors.toList());

		if (collect.isEmpty()) {
			throw new ProductNotFoundException("No Product Found");
		}

		List<FetchProductBinding> productFetch = new ArrayList<>();

		collect.forEach(x -> {

			FetchProductBinding binding = new FetchProductBinding();

			ImageModel imageModel = imageModelRepository.findById(x.getImageModelId()).get();
			String imageUrl = imageModel.getImageUrl();


			binding.setImageUrls(imageUrl);

			BeanUtils.copyProperties(x, binding);

			productFetch.add(binding);

		});

		return productFetch;

	}
	
	@Override
	public String getImageUrlById(Integer productId) {
	
		Integer imageId = productRepository.findById(productId).get().getImageModelId();
		
		String imageUrl = imageModelRepository.findById(imageId).get().getImageUrl();
		
		return imageUrl;
	}
}
