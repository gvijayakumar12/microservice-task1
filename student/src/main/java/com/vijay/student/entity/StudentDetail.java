package com.vijay.student.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentDetail {
	
	 private Long id;

	 private String name;

	 private String email;
	    
	 private Course course;

}
