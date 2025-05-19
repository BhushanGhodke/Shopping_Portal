package com.tcs.flipkart.register.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.flipkart.register.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
	public User findByUsernameAndPassword(String username, String password);
}
