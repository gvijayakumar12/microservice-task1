package com.vijay.authentication.Auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vijay.authentication.Auth.model.RequestGenerateToken;
import com.vijay.authentication.Auth.model.UserDetail;
import com.vijay.authentication.Auth.repo.UserDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService{
	
	private final UserDetailRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;

	@Override
	public String register(UserDetail detail) {
		
		detail.setPassword(passwordEncoder.encode(detail.getPassword()));
		repository.save(detail);
		
		return "Save User Success";
	}

	@Override
	public String generateToken(RequestGenerateToken request) {
		UserDetail userDetail =  repository.findByEmail(request.getEmail()).get();
		
		return jwtService.generateToken(String.valueOf(userDetail.getId()), userDetail.getEmail());
	}

	@Override
	public boolean validateToken(String token) {
		return !jwtService.isTokenExpired(token);
	}
	

}
