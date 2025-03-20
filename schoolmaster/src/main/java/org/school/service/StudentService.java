package org.school.service;

import org.school.model.Student;
import org.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

}
