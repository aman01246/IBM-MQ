package com.ibmmq.config;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;

import jakarta.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.JacksonJsonMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class MQConfig {

	 @Value("${ibm.mq.queue-manager}")
	    private String queueManager;

	    @Value("${ibm.mq.channel}")
	    private String channel;

	    @Value("${ibm.mq.conn-name}")
	    private String connectionName;

	    @Value("${ibm.mq.user}")
	    private String username;

	    @Value("${ibm.mq.password}")
	    private String password;

	    @Bean
	    public ConnectionFactory connectionFactory() throws Exception {
	        MQConnectionFactory factory = new MQConnectionFactory();

	        String[] parts = connectionName.replace("(", " ")
	                .replace(")", "").split("\\s+");

	        factory.setHostName(parts[0]);
	        factory.setPort(Integer.parseInt(parts[1]));
	        factory.setQueueManager(queueManager);
	        factory.setChannel(channel);
	        factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);

	        factory.setStringProperty(WMQConstants.USERID, username);
	        factory.setStringProperty(WMQConstants.PASSWORD, password);

	        return factory;
	    }

	    @Bean
	    public MessageConverter jacksonJmsMessageConverter() {
	        JacksonJsonMessageConverter converter =
	                new JacksonJsonMessageConverter();

	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");

	        return converter;
	    }

	    @Bean
	    public JmsTemplate jmsTemplate(
	            ConnectionFactory connectionFactory,
	            MessageConverter jacksonJmsMessageConverter) {

	        JmsTemplate template = new JmsTemplate(connectionFactory);
	        template.setMessageConverter(jacksonJmsMessageConverter);
	        return template;
	    }

	    @Bean
	    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
	            ConnectionFactory connectionFactory,
	            MessageConverter jacksonJmsMessageConverter) {

	        DefaultJmsListenerContainerFactory factory =
	                new DefaultJmsListenerContainerFactory();

	        factory.setConnectionFactory(connectionFactory);
	        factory.setMessageConverter(jacksonJmsMessageConverter);

	        return factory;
	    }
}
