package com.vijay.authentication.Auth.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.authentication.Auth.model.RequestGenerateToken;
import com.vijay.authentication.Auth.model.UserDetail;
import com.vijay.authentication.Auth.service.UserDetailService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserDetailService service;
	
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public String register(@RequestBody UserDetail request) {
		return service.register(request);
	}
	
	@PostMapping("/getToken")
	public String getToken(@RequestBody RequestGenerateToken request) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return service.generateToken(request);
		} else {
			throw new RuntimeException("Invalid access");
		}
		
	}
	
	
	@GetMapping("/validateToken/{token}")
	public boolean validateToken(@PathVariable String token) {
		return service.validateToken(token);
	}
}
