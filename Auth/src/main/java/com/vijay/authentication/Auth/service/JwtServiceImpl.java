package com.vijay.authentication.Auth.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	private static final String SECURITY_KEY = "6D5971337436773979244226452948404D635166546A576E5A72347537782141";

	@Override
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	@Override
	public String generateToken(String userId, String userEmail) {
		Map<String, Object> map = new HashMap<>();
		map.put("KEY_USER_ID", userId);
		map.put("KEY_USER_NAME", userEmail);
		return generateToken(map, userEmail);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return !isTokenExpired(token) && (userName.equals(userDetails.getUsername()));
	}

	@Override
	public boolean isTokenExpired(String token) {
		final Claims claims = extractAllClaims(token);
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	@Override
	public String generateToken(Map<String, Object> extraClaims, String username) {
		return Jwts.builder().setClaims(extraClaims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24)))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}
	
	@Override
	public void isTokenValid(String token) {
		Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
