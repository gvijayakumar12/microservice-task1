package com.vijay.authentication.Auth.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String generateToken(Map<String, Object> extraClaims, String userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
    
    void isTokenValid(String token);

    String generateToken(String userId, String userEmail);

    <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    String extractUserName(String token);
    
    boolean isTokenExpired(String token);

}
