package com.vijay.course;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
	    
	    String userId =	request.getHeader("KEY_USER_ID");
	    String userName = request.getHeader("KEY_USER_NAME");
		
		
		if (userId != null && userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			AuditUser user = AuditUser.builder().username(userName).id(userId).build();
			UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken(
					user,
					null,
					user.getAuthorities());
			
			//authUser.setAuthenticated(true);
			authUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authUser);
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
