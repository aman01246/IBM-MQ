package com.ibmmq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ibmmq.model.Order;

@Service
public class OrderConsumer {

	@JmsListener(destination = "QM2.ORDER.QUEUE", 
			containerFactory = "jmsListenerContainerFactory")
	public void receiveOrder(Order order) {

		System.out.println("Order received from IBM MQ!");
		System.out.println("Order details: " + order);
	}
}
