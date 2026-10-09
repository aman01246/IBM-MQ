package com.ibmmq.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MQProducer {

	  private final JmsTemplate jmsTemplate;

	    public void sendMessage(String message) {

	        jmsTemplate.convertAndSend(
	                "DEV.QUEUE.1",
	                message
	        );

	        System.out.println("Message sent: " + message);
	    }
}
