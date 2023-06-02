package com.vijay.gateway.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Claims;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	@Autowired
	private RouteValidator validator;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	public static class Config {
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			
			if(validator.isSecured.test(exchange.getRequest())) {				
				
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Authorization token is missing in header");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader == null || !authHeader.startsWith("Bearer ")) {
					throw new RuntimeException("Invalid Access Token");
				}
				
				authHeader = authHeader.substring(7);
				boolean isTokenExpried = jwtUtil.isTokenExpired(authHeader);
				if(isTokenExpried) {
					throw new RuntimeException("Access Token Expired");
				}
				
				Claims claims = jwtUtil.extractAllClaims(authHeader);
				String userId = (String) claims.get("KEY_USER_ID");
				String userName = (String) claims.get("KEY_USER_NAME");
				
				ServerHttpRequest newReq = exchange.getRequest().mutate()
						.header("KEY_USER_ID", userId)
						.header("KEY_USER_NAME", userName)
						.build();
				ServerWebExchange mutatedExchange = exchange.mutate().request(newReq).build();
			    
				return chain.filter(mutatedExchange);
			}
			
			return chain.filter(exchange);
		});
		
	}

}
