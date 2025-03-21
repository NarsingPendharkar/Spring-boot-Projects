package org.school.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.StudentDTO;
import org.school.DTO.StudentResponseDTO;
import org.school.entity.ClassEntity;
import org.school.entity.Student;
import org.school.entity.User;
import org.school.service.ClassEntityService;
import org.school.service.StudentService;
import org.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class Admincontroller {
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ClassEntityService classService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "/user/login";
	}

	@GetMapping("/logout")
	public String logoutPage() {
		return "redirect:login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "/user/register";
	}

	@PostMapping("/saveuser")
	public String saveUser(@ModelAttribute User newuser) {
		String passwordcode = newuser.getPassword();
		newuser.setPassword(passwordEncoder.encode(passwordcode));
		userService.save(newuser);
		System.out.println(newuser.toString());
		return "redirect:login";
	}

	@GetMapping("/dashboards")
	public String dashboardPage(Model model) {
		Authentication authuser = SecurityContextHolder.getContext().getAuthentication();
		//String role = authuser.getAuthorities().toArray()[0].toString();
		String  role =authuser.getAuthorities().iterator().next().getAuthority();
		
		if (role.equalsIgnoreCase("ADMIN")) {
			List<Student> allStudents = studentService.findallStudent();
			List<StudentResponseDTO> response = modelMapper.map(allStudents, new TypeToken<List<StudentDTO>>() {}.getType());
			model.addAttribute("allStudents", response);
			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("TEACHER")) {
			// Fetch all students assigned to this teacher from DB and add to model
			// ...
			return "/dashboards/teacherdashboard";
		} else if (role.equalsIgnoreCase("STUDENT")) {
			// Fetch student data and add to model
			// ...
			return "/dashboards/studentdashboard";
		} else if (role.equalsIgnoreCase("PARENT")) {
			// Fetch parent data and add to model
			// ...
			return "/dashboards/parentdashboard";
		} else {
			return "redirect:/login";
		}

	}

	@PostMapping("/admin/savestudent")
	public ResponseEntity<String> saveStudent(@ModelAttribute StudentDTO studentDTO) {
		// Map DTO to Student (Student already extends User)
		Student student = modelMapper.map(studentDTO, Student.class);

		// Encode password and set role
		
		// Fetch ClassEntity from DB instead of mapping directly
		ClassEntity userClass = classService.findbyclassname(studentDTO.getClassName());
		student.setAssignedClass(userClass);

		// Save Student (which is also User)
		studentService.saveStudent(student);

		return ResponseEntity.ok("Student saved successfully");
	}

	@GetMapping("/fetchstudent")
	@ResponseBody
	public List<StudentDTO> fetchStudent(Model model) {
	    List<Student> students = studentService.findallStudent();
	  
	    // Correct way to map a list of entities to a list of DTOs
	    List<StudentDTO> studentlist = modelMapper.map(students, new TypeToken<List<StudentDTO>>() {}.getType());
	    model.addAttribute("allstudents", studentlist);
	    return studentlist;
	}

}
