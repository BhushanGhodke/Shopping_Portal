package com.tcs.flipkart.order.mapper;

import org.modelmapper.ModelMapper;

import com.tcs.flipkart.order.dto.OrderDTO;
import com.tcs.flipkart.order.entity.Order;


public class OrderMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public static OrderDTO convertToDto(Order order) {
		return mapper.map(order, OrderDTO.class);
	}

	public static Order convertToEntity(OrderDTO orderDTO) {
		return mapper.map(orderDTO, Order.class);
	}

}