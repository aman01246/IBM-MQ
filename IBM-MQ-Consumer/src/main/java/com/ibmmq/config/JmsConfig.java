package com.ibmmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.JacksonJsonMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import jakarta.jms.ConnectionFactory;

@Configuration
public class JmsConfig {

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();

		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");

		return converter;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory,
			MessageConverter jacksonJmsMessageConverter) {

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jacksonJmsMessageConverter);

		return factory;
	}
}
