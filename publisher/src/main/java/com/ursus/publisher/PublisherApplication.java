package com.ursus.publisher;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PublisherApplication {
	// nie posiada bazy danych, musi pobrać za pośrednictwem REST API z bazy danych z aplikacji Students
	// Komunikacja musi być synchroniczna, czyli Publisher czeka na odpowiedź od Students
	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

	@Bean
	public MessageConverter messageConverter() {
		// bean, który będzie konwertował obiekty na JSON
		return  new Jackson2JsonMessageConverter();
	}

	@Bean
	public RestTemplate restTemplate() {
		// bean, który będzie wykonywał zapytania HTTP
		return new RestTemplate();
	}
}
