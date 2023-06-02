package com.vijay.course.service;

import java.util.List;

import com.vijay.course.entity.Course;

public interface CourseService {

	Course add(Course course);
	
	Course update(Long id, Course course);
	
	Course getCourse(Long id);
	
	List<Course> getAllCourse();
	
	boolean deleteCourse(Long id);
}
