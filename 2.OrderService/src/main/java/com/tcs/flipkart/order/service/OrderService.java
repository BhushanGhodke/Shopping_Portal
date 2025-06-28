package com.tcs.flipkart.order.service;

import java.util.List;

import com.tcs.flipkart.order.dto.OrderDTO;
import com.tcs.flipkart.order.dto.OrderResponse;

public interface OrderService {

	public List<OrderDTO> createOrder(List<OrderDTO> orderDTO);
	
	public boolean cancelOrder(Integer orderId);
	
	public List<OrderResponse> getOrderListByUser(Integer userId);
	
	public void updateOrderStatus();
}
