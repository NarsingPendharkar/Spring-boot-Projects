package com.School360.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.School360.model.Student;
import com.School360.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentrepo;

	public Student admitstd(Student student) {
		return studentrepo.save(student);
	}
	public List<Student> getallstd(){
		return studentrepo.findAll();
	}
}
