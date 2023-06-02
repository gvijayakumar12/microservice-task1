package com.vijay.course.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.course.entity.Course;
import com.vijay.course.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
	
	private final CourseService service;
	
	@PostMapping("")
	public Course addCourse(@RequestBody Course request) {
		return service.add(request);
	}
	
	@PutMapping("/{id}")
	public Course updateCourse(@PathVariable Long id, @RequestBody Course request) {
		return service.update(id, request);
	}
	
	@GetMapping("/{id}")
	public Course getCourse(@PathVariable Long id) {
		return service.getCourse(id);
	}
	
	@GetMapping("")
	public List<Course> getAllCourse() {
		return service.getAllCourse();
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteCourse(@PathVariable Long id) {
		return service.deleteCourse(id);
	}

}
