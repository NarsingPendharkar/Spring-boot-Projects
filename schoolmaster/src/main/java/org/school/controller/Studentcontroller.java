package org.school.controller;

import org.modelmapper.ModelMapper;
import org.school.DTO.StudentData;
import org.school.entity.Student;
import org.school.entity.User;
import org.school.service.StudentService;
import org.school.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;

@Controller
public class Studentcontroller {

	private final Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/teacher/savestudent") 
	@Transactional
		public ResponseEntity<String>saveStudent(@ModelAttribute StudentData data) { 
		 // Save User
	    User newUser = data.getUser();
	    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	    User savedUser = userService.save(newUser);
	    // Save Student with reference to User
	    Student student = data.getStudent();
	    student.setUser(savedUser); // Link Student to the saved User
	    studentService.saveStudent(student);
	    return ResponseEntity.ok("Student saved successfully");
	    }
	

}
