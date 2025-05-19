package com.tcs.flipkart.register.service;

import com.tcs.flipkart.register.dto.LoginForm;
import com.tcs.flipkart.register.dto.RegistrationFrom;
import com.tcs.flipkart.register.dto.UserBinding;

public interface UserService {

	public boolean registerUser(RegistrationFrom registrationFrom);
	
	public UserBinding loginCheck(LoginForm loginForm);
	
	public UserBinding getUserInfoById(Integer id);
	
	public UserBinding updateUserInfo(UserBinding userBinding);
	
}
