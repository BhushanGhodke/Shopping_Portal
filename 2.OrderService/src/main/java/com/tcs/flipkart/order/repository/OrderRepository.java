package com.tcs.flipkart.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcs.flipkart.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	
	public List<Order> findByUserId(Integer userId);
	
	@Query(value = "select * from order_table where user_id=? order by order_date desc", nativeQuery = true)
	public List<Order> getOrderListByUserId(Integer userId);
}
