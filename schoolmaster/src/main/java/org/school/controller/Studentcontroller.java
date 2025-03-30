package org.school.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.StudentDTO;
import org.school.DTO.UserDTO;
import org.school.entity.Student;
import org.school.entity.User;
import org.school.service.ClassEntityService;
import org.school.service.StudentService;
import org.school.service.TeacherService;
import org.school.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class Studentcontroller {
	
	private final Logger logger =LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ClassEntityService classService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/fetchstudent")
	@ResponseBody
	public List<StudentDTO> fetchStudent(Model model) {
	    List<Student> students = studentService.findallStudent();
	    // Correct way to map a list of entities to a list of DTOs
	    List<StudentDTO> studentlist = modelMapper.map(students, new TypeToken<List<StudentDTO>>() {}.getType());
	    model.addAttribute("allstudents", studentlist);
	    return studentlist;
	}
	
	/*
	 * @PostMapping("/admin/savestudent") public ResponseEntity<String>
	 * saveStudent(@ModelAttribute StudentDTO studentDTO) { // Map DTO to Student
	 * (Student already extends User) Student student = modelMapper.map(studentDTO,
	 * Student.class);
	 * 
	 * // Encode password and set role
	 * 
	 * // Fetch ClassEntity from DB instead of mapping directly ClassEntity
	 * userClass = classService.findbyclassname(studentDTO.getClassName());
	 * student.setAssignedClass(userClass);
	 * 
	 * // Save Student (which is also User) studentService.saveStudent(student);
	 * 
	 * return ResponseEntity.ok("Student saved successfully"); }
	 */

	

}
