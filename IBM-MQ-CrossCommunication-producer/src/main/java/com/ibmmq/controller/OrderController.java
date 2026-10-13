package com.ibmmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibmmq.model.Order;
import com.ibmmq.service.OrderProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderProducer orderProducer;

	@PostMapping
	public ResponseEntity<String> sendOrder(@RequestBody Order order) {

		orderProducer.sendOrder(order);

		return ResponseEntity.ok("Order sent to IBM MQ: " + order.getId());
	}
}
