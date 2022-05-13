package com.uos.intgtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class IntegrationTaskConfig {
	
	@Bean
	public RestTemplate paymentSystemRestTemplate() {
		log.debug("Created rest template for Scheduler API calls.");
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		return restTemplate;
	}

}
