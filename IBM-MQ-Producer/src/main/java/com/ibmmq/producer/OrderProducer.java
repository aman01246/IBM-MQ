package com.ibmmq.producer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ibmmq.model.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProducer {

	private final JmsTemplate jmsTemplate;

	public String sendMessage(Order order) {
		
		 System.out.println(
			        "Active converter: " +
			        jmsTemplate.getMessageConverter().getClass().getName()
			    );

		jmsTemplate.convertAndSend("DEV.QUEUE.1", order);

		return "Message Send to IBM MQ.";
	}
}
