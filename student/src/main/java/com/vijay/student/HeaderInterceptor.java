/**
 * 
 */
package com.vijay.student;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author Vijay
 *
 */
public class HeaderInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		ClientHttpResponse response = execution.execute(request, body);
		response.getHeaders().add("Authorization", "Bearer lakdfai");
		
		return response;
	}

}
