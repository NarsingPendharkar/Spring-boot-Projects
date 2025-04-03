package org.school.service;

import java.util.List;

import org.school.DTO.StudentCourseDTO;
import org.school.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	/*
	 * public List<StudentCourseDTO> getenrolledCourses(long id){ return
	 * enrollmentRepository.getStudentEnrolledCourses(id); }
	 */


}
