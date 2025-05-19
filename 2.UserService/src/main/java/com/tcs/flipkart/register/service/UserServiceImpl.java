package com.tcs.flipkart.register.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.flipkart.register.dto.LoginForm;
import com.tcs.flipkart.register.dto.RegistrationFrom;
import com.tcs.flipkart.register.dto.UserBinding;
import com.tcs.flipkart.register.entity.User;
import com.tcs.flipkart.register.exception.InvalidUsernameAndPassWordEx;
import com.tcs.flipkart.register.exception.UserNotFoundException;
import com.tcs.flipkart.register.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerUser(RegistrationFrom registrationFrom) {
		User email = null;
		User savedUser = null;
		try {
			email = userRepository.findByEmail(registrationFrom.getEmail());
			if (email == null) {

				User user = new User();

				BeanUtils.copyProperties(registrationFrom, user);

				savedUser = userRepository.save(user);
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return savedUser.getId() != null;
	}

	@Override
	public UserBinding loginCheck(LoginForm loginForm) {

		User user = userRepository.findByEmail(loginForm.getEmail());
		System.out.println(user);

		if (user.getPassword().equals(loginForm.getPassword())) {
			UserBinding userBinding = new UserBinding();
			BeanUtils.copyProperties(user, userBinding);
			return userBinding;
		} else {
			throw new InvalidUsernameAndPassWordEx("Invalid Username or Password");
		}

	}

	@Override
	public UserBinding getUserInfoById(Integer id) {

		User user = userRepository.findById(id).orElseThrow();

		if (user.getId() != null) {

			UserBinding userBinding = new UserBinding();
			BeanUtils.copyProperties(user, userBinding);
			return userBinding;
		} else {
			throw new UserNotFoundException("User Not Found with Id:: " + id);
		}
	}

	@Override
	public UserBinding updateUserInfo(UserBinding userBinding) {

	User userInfo = userRepository.findById(userBinding.getId()).get();

		UserBinding user = new UserBinding();

		if (userInfo.getId()!=null) {
			BeanUtils.copyProperties(userBinding, userInfo);
			User savedUser = userRepository.save(userInfo);
			BeanUtils.copyProperties(savedUser, user);
			System.out.println(user.toString());
			return user;
		} else {

			return null;
		}

	}
}
