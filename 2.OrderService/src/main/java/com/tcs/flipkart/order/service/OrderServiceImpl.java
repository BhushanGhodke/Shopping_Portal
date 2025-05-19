package com.tcs.flipkart.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.flipkart.order.client.CartServiceClient;
import com.tcs.flipkart.order.client.InventoryServiceClient;
import com.tcs.flipkart.order.dto.OrderDTO;
import com.tcs.flipkart.order.dto.OrderResponse;
import com.tcs.flipkart.order.entity.Order;
import com.tcs.flipkart.order.mapper.OrderMapper;
import com.tcs.flipkart.order.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartServiceClient cartServiceClient;
	
	@Autowired
	private InventoryServiceClient inventoryServiceClient;
	
	@Override
	@Transactional
	public List<OrderDTO> createOrder(List<OrderDTO> orderDTO) {

		 List<Order> orderEntities = orderDTO.stream()
                 .map(OrderMapper::convertToEntity)
                 .collect(Collectors.toList());
		List<Order> orders=orderRepository.saveAll(orderEntities);
		
		if(!orders.isEmpty()) {
			
			orderDTO.forEach(x->{
		          
			 cartServiceClient.deleteCartById(x.getCartId());
		              
			});
		}
		
		 return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	    
	  
	}

	@Override
	public boolean cancelOrder(Integer orderId) {
	
		Optional<Order> order = orderRepository.findById(orderId);
		
		if(order.isPresent()) {
			orderRepository.delete(order.get());
		    return true;
		}else {
			return false;
		}
	}
	
	@Override
	public List<OrderResponse> getOrderListByUser(Integer userId) {
	
		List<Order> orderList = orderRepository.getOrderListByUserId(userId);
		
		List<OrderResponse> response= new ArrayList<OrderResponse>();
		
		orderList.forEach(x->{
			
			String imageUrl = inventoryServiceClient.getImageDetailsById(x.getProductId()).getBody();
			
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setOrderId(x.getOrderId());
			orderResponse.setOrderDate(x.getOrderDate());
			orderResponse.setOrderStatus(x.getOrderStatus());
			orderResponse.setPaymentType(x.getPaymentType());
			orderResponse.setPrice(x.getPrice());
			orderResponse.setQuantity(x.getQuantity());
			orderResponse.setUserId(x.getUserId());
			orderResponse.setImageUrl(imageUrl);
			
			response.add(orderResponse);
			imageUrl="";
		});
		return response;
	}
	
}
