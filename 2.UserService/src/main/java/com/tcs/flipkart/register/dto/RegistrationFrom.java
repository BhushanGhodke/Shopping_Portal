package com.tcs.flipkart.register.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFrom {

private Integer id;
	
	private String name;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String mobile;
	
	@CreationTimestamp
	private LocalDate createdDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
