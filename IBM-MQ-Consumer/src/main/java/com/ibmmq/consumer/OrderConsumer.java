package com.ibmmq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ibmmq.model.Order;

@Service
public class OrderConsumer {

	 @JmsListener(destination = "DEV.QUEUE.1")
	    public void receiveMessage(Order order) {

	        System.out.println("Order received successfully!");
	        System.out.println("ID: " + order.getId());
	        System.out.println("Name: " + order.getName());
	        System.out.println("Price: " + order.getPrice());
	    }
}
