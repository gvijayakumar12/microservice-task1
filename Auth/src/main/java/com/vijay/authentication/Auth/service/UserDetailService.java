package com.vijay.authentication.Auth.service;

import com.vijay.authentication.Auth.model.RequestGenerateToken;
import com.vijay.authentication.Auth.model.UserDetail;

public interface UserDetailService {

	String register(UserDetail detail);
	
	String generateToken(RequestGenerateToken request);
	
	boolean validateToken(String token);
	
}
