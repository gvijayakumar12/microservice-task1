package com.vijay.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vijay.course.entity.Course;
import com.vijay.course.repo.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository repository;

	@Override
	public Course add(Course course) {
		return repository.save(course);
	}

	@Override
	public Course update(Long id, Course course) {
		Course oldCourse = repository.findById(id).orElseThrow(null);
		
		oldCourse.setName(course.getName());
		oldCourse.setDuration(course.getDuration());
		
		return repository.save(oldCourse);
	}

	@Override
	public Course getCourse(Long id) {
		Course oldCourse = repository.findById(id).orElseThrow(null);
		return oldCourse;
	}

	@Override
	public List<Course> getAllCourse() {
		return repository.findAll();
	}

	@Override
	public boolean deleteCourse(Long id) {
		boolean isDeleted = false;
		try {
			repository.findById(id).orElseThrow(() -> new Exception("No Found"));
			repository.deleteById(id);
			isDeleted = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return isDeleted;
	}

}
