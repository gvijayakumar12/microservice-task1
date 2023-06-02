package com.vijay.student.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vijay.student.HeaderInterceptor;
import com.vijay.student.entity.Course;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiCall {

	private final RestTemplate restTemplate;
	
	private static final String courseMicroServiceBaseUrl = "http://course-module";
	
	public Course getCourseDetail(Long courseId) {
		
		Course course = restTemplate.getForObject(courseMicroServiceBaseUrl+"/api/v1/course/{courseId}", 
				Course.class, courseId);		
		return course;
	}
}
