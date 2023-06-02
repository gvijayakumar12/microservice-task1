package com.vijay.student.service;

import java.util.List;

import com.vijay.student.entity.Student;
import com.vijay.student.entity.StudentDetail;

public interface StudentService {
	
	Student add(Student request);
	
	Student update(Long id, Student request);
	
	Student getById(Long id);
	
	List<Student> getAllStudents();
	
	boolean deleteStudentById(Long id);
	
	StudentDetail getStudentDetail(Long id);
	
	StudentDetail getStudentDetailByWebClient(Long id);

}
