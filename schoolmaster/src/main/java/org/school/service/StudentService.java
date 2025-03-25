package org.school.service;

import java.util.List;

import org.school.entity.Student;
import org.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	public List<Student> findallStudent() {
		return studentRepository.findAll();
	}

	// find by id
	public Student findById(Long id) {
		return studentRepository.findById(id).orElse(null);
	}

}
