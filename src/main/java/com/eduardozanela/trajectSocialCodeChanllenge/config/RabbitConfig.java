package com.eduardozanela.trajectSocialCodeChanllenge.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Configuration
public class RabbitConfig {
	
	@Bean
	public TopicExchange getExchangeSubscription() {
		return new TopicExchange(RabbitMqConstants.PROCESS_WEB_SCRAPER_EXCHANGE);
	}   
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return new Jackson2JsonMessageConverter(mapper);
	}
	
	@Bean
	public Queue queue() {
		return QueueBuilder.durable(RabbitMqConstants.PROCESS_WEB_SCRAPER_QUEUE).withArgument(RabbitMqConstants.XDLE, "")
				.withArgument(RabbitMqConstants.XDLRK, RabbitMqConstants.PROCESS_WEB_SCRAPER_DEAD_QUEUE).build();
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(getExchangeSubscription())
						.with(RabbitMqConstants.PROCESS_WEB_SCRAPER_KEY);
	}
}

