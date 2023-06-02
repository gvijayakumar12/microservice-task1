package com.vijay.student.service;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;
import com.vijay.student.AddHeaderExchangeFilter;
import com.vijay.student.entity.Course;

import reactor.core.publisher.Mono;

@Service
public class ApiCallUsingWebClient {

	private static final String courseMicroServiceBaseUrl = "http://course-module";
	
	private WebClient.Builder loadBalancerWebClientBuilder;
	
	public ApiCallUsingWebClient(WebClient.Builder webClientBuilder,
			ReactorLoadBalancerExchangeFilterFunction lbFunction) {
		this.loadBalancerWebClientBuilder = webClientBuilder;
	}
	
	public Mono<Course> getCourseDetail(Long id) {
		return loadBalancerWebClientBuilder.build().get()
				.uri(courseMicroServiceBaseUrl+"/api/v1/course/"+id)
				.headers((httpHeader) -> {
					httpHeader.add("KEY_USER_ID", "2");
					httpHeader.add("KEY_USER_NAME", "a@mail.com");
				})
				.retrieve().bodyToMono(Course.class);
	}
	
	private ExchangeFilterFunction logFilter() {
	    return (clientRequest, next) -> {
	    	
	    	
	    	
	        return next.exchange(clientRequest);
	    };
	}
}
