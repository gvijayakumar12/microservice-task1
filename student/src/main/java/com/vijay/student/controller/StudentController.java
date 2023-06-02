package com.vijay.student.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.student.entity.Student;
import com.vijay.student.entity.StudentDetail;
import com.vijay.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService service;
	
	@PostMapping("")
	public Student addStudent(@RequestBody Student request) {
		return service.add(request);
	}
	
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student request) {
		return service.update(id, request);
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("")
	public List<Student> getAllStudent() {
		return service.getAllStudents();
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteStudent(@PathVariable Long id) {
		return service.deleteStudentById(id);
	}
	
	@GetMapping("/studentDetail/{id}")
	public StudentDetail getStudentDetail(@PathVariable Long id) {
		return service.getStudentDetail(id);
	}
	
	@GetMapping("/studentDetailByWebclient/{id}")
	public StudentDetail getStudentDetailByWebClient(@PathVariable Long id) {
		return service.getStudentDetailByWebClient(id);
	}
}
