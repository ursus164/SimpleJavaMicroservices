package com.ursus.reciever;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecieverApplication.class, args);
	}

	@Bean
	public MessageConverter messageConverter() {
		// bean, który będzie konwertował obiekty na JSON
		return  new Jackson2JsonMessageConverter();
	}
}
