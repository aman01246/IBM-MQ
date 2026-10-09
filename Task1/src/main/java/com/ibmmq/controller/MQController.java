package com.ibmmq.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibmmq.service.MQProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mq")
public class MQController {

	private final MQProducer mqProducer;

	@PostMapping("/send")
	public String sendMessage(@RequestParam String message) {

		mqProducer.sendMessage(message);

		return "Message sent successfully";
	}
}
