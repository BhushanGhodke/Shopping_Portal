package com.tcs.flipkart.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.flipkart.product.entity.ImageModel;
import com.tcs.flipkart.product.repository.ImageModelRepository;

@Service
public class ImageModelServiceImpl  implements ImageModelService{
 
	
	@Autowired
	private ImageModelRepository imageRepository;
	
	@Override
	public ImageModel saveImageUrlToDB(ImageModel imageModel) {
		
		ImageModel savedImageUrl = imageRepository.save(imageModel);
		
		return savedImageUrl;
	}

}
