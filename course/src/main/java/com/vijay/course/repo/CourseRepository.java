package com.vijay.course.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
