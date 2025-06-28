package com.tcs.flipkart.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.flipkart.order.dto.OrderDTO;
import com.tcs.flipkart.order.dto.OrderResponse;
import com.tcs.flipkart.order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

	
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/create")

	public ResponseEntity<String> createOrder(@RequestBody List<OrderDTO> orderDTO) {
		

		List<OrderDTO> orderList = orderService.createOrder(orderDTO);
		if (orderList.size() >= 1) {

			return new ResponseEntity<>("Order Created", HttpStatus.CREATED);
		} else {
			throw new RuntimeException("Order Creation Failure");
		}
	}

	@DeleteMapping("/cancel/{orderId}")
	public ResponseEntity<Boolean> cancelOrder(@PathVariable Integer orderId) {
		boolean status = orderService.cancelOrder(orderId);

		if (status) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		} else {
			throw new RuntimeException("Order Cancelled Failure");
		}
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<List<OrderResponse>> getOrderById(@PathVariable Integer userId) {
		List<OrderResponse> orderResponse = orderService.getOrderListByUser(userId);
		return new ResponseEntity<>(orderResponse, HttpStatus.OK);

	}
}
