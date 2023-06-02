package com.vijay.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Bean
	@LoadBalanced
    RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
//		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//		interceptors.add(new HeaderInterceptor());
//		restTemplate.setInterceptors(interceptors);
		
		return restTemplate;
	}
	
	@Bean
	@LoadBalanced
    WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
