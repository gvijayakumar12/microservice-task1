package com.vijay.student.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vijay.student.entity.Course;
import com.vijay.student.entity.Student;
import com.vijay.student.entity.StudentDetail;
import com.vijay.student.repo.StudentRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository repository ;
	
	private final ApiCall apiCall;
	
	private final ApiCallUsingWebClient apiCallUsingWebClient;
	
	@Override
	public Student add(Student request) {
		return repository.save(request);
	}

	@Override
	public Student update(Long id, Student request) {
		Student oldStudent = repository.findById(id).orElseThrow(null);
		
		oldStudent.setName(request.getName());
		oldStudent.setEmail(request.getEmail());
		oldStudent.setCourseId(request.getCourseId());
	
		return repository.save(oldStudent);
	}

	@Override
	public Student getById(Long id) {
		Student student = repository.findById(id).orElseThrow(null);
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	@Override
	public boolean deleteStudentById(Long id) {
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
	
	@Override
	public StudentDetail getStudentDetail(Long id) {
		Student student = repository.findById(id).orElseThrow(null);
		
		Course course = apiCall.getCourseDetail(student.getCourseId());
		
		StudentDetail studentDetail = new StudentDetail();
		BeanUtils.copyProperties(student, studentDetail);
		studentDetail.setCourse(course);
		
		return studentDetail;
	}
	
	@Override
	public StudentDetail getStudentDetailByWebClient(Long id) {
		Student student = repository.findById(id).orElseThrow(null);
		Mono<Course> course = apiCallUsingWebClient.getCourseDetail(student.getCourseId());
		
		StudentDetail studentDetail = new StudentDetail();
		BeanUtils.copyProperties(student, studentDetail);
		studentDetail.setCourse(course.block());
		
		return studentDetail;
	}

}
