package com.ibmmq.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ibmmq.model.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProducer {

	private final JmsTemplate jmsTemplate;
	
	public void sendOrder(Order order) {
		jmsTemplate.convertAndSend("QM2.ORDER.REMOTE", order);
	}
}
