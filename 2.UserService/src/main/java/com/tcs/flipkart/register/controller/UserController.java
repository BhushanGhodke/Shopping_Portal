package com.tcs.flipkart.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.flipkart.register.dto.ApiResponse;
import com.tcs.flipkart.register.dto.LoginForm;
import com.tcs.flipkart.register.dto.RegistrationFrom;
import com.tcs.flipkart.register.dto.UserBinding;
import com.tcs.flipkart.register.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegistrationFrom registrationFrom) {
		boolean status = userService.registerUser(registrationFrom);

		if (status) {
			ApiResponse<String> response = new ApiResponse<String>();
			response.setErrorCode(201);
			response.setMessage("Registration Successfull");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.CREATED);
		} else {

			ApiResponse<String> response = new ApiResponse<String>();
			response.setErrorCode(500);
			response.setMessage("Registration Failed");
			return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.CREATED);

		}

	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserBinding>> LoginUser(@RequestBody LoginForm loginForm) {

		ApiResponse<UserBinding> response= new  ApiResponse<UserBinding>();
		UserBinding userInfo = userService.loginCheck(loginForm);

		response.setData(userInfo);
		response.setErrorCode(200);
		response.setMessage("Login Success");
		
		return new ResponseEntity<ApiResponse<UserBinding>>(response, HttpStatus.OK);

	}

	@GetMapping("/getUserInfo/{id}")
	public ResponseEntity<UserBinding> getUserInfoById(@PathVariable Integer id) {
		UserBinding userBinding = userService.getUserInfoById(id);
		
		ApiResponse<UserBinding> response= new  ApiResponse<UserBinding>();
	
		response.setData(userBinding);
		response.setErrorCode(200);
		response.setMessage("User Info Fetch Successfully");

		return new ResponseEntity<>(userBinding, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<UserBinding> updateUserInfo(@RequestBody UserBinding userBinding) {
		System.out.println("update " + userBinding);
		UserBinding userInfo = userService.updateUserInfo(userBinding);

		ApiResponse<UserBinding> response= new  ApiResponse<UserBinding>();

		response.setData(userInfo);
		response.setErrorCode(200);
		response.setMessage("User updated successfully");
		
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}
}
