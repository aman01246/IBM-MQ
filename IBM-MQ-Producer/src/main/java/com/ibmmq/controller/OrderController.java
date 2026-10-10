package com.ibmmq.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibmmq.model.Order;
import com.ibmmq.producer.OrderProducer;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderProducer orderProducer;
	
	@PostMapping("/order")
	public String postMethodName(@RequestBody Order order) {
		
		String msg = orderProducer.sendMessage(order);
		
		return msg;
	}
	
}
