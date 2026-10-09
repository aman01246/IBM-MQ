package com.ibmmq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MQConsumer {

	 @JmsListener(destination = "DEV.QUEUE.1")
	    public void receiveMessage(String message) {

	        System.out.println(
	                "Message received from IBM MQ: " + message
	        );
	    }
}
