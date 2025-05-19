package com.tcs.flipkart.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="image_table")
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageModelId;

	private String imageUrl;
	
	public Integer getImageModelId() {
		return imageModelId;
	}

	public void setImageModelId(Integer imageModelId) {
		this.imageModelId = imageModelId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

	public ImageModel(Integer id, String imageUrl) {
		super();
		this.imageModelId = id;
		
		this.imageUrl = imageUrl;
	}

	public ImageModel() {
		
	}
	
	
	
	
}
