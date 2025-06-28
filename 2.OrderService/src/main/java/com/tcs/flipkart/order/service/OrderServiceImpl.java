package com.tcs.flipkart.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartServiceClient cartServiceClient;
	
	@Autowired
	private InventoryServiceClient inventoryServiceClient;
	
	@Override
	@Transactional
	public List<OrderDTO> createOrder(List<OrderDTO> orderDTO) {
        
		logger.info("createOrder() execution started  ");
		
		 List<Order> orderEntities = orderDTO.stream()
                 .map(OrderMapper::convertToEntity)
                 .collect(Collectors.toList());
		 
		List<Order> orders=orderRepository.saveAll(orderEntities);
		
		if(!orders.isEmpty()) {
			
			orderDTO.forEach(x->{
		          
			 cartServiceClient.deleteCartById(x.getCartId());
		              
			});
		}
		logger.info("createOrder() execution end successfully");
		 return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	    
	  
	}
	
	
	

	@Override
	public boolean cancelOrder(Integer orderId) {

		logger.info("cancelOrder() execution started");
		
		Optional<Order> order = orderRepository.findById(orderId);
		
		if(order.isPresent()) {
			orderRepository.delete(order.get());

			logger.info("cancelOrder() execution stopped");
		    return true;
		}else {

			logger.info("cancelOrder() executed but not cancel order");
			return false;
		}
	}
	
	@Override
	public List<OrderResponse> getOrderListByUser(Integer userId) {


		logger.info("getOrderListByUser() execution started :: "+userId);
		
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

		logger.info("getOrderListByUser() execution stopped ");
		return response;
	}
	
	@Override
	public void updateOrderStatus() {
	
		List<Order> orderList = orderRepository.findAll();
		
		orderList.forEach(x->{
			
			if(x.getOrderStatus().equalsIgnoreCase("PLACED")) {
				
			    LocalDateTime orderDate = x.getOrderDate();
			    
				Period period = Period.between(orderDate.toLocalDate(), LocalDate.now()) ;
				
				int months = period.getMonths();
				
				int days = period.getDays();
				
				System.out.println(months);
				System.out.println(days);
				if((months*30+days)>=7) {
					x.setOrderStatus("DELIVERD");
					orderRepository.save(x);	
				}
				
			 	
			}
		});
		
	}
	
}
