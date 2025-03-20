package org.school.controller;

import org.school.model.Student;
import org.school.model.User;
import org.school.repository.StudentRepository;
import org.school.service.StudentService;
import org.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Admincontroller {
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

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
	public String dashboardPage() {
		Authentication authuser = SecurityContextHolder.getContext().getAuthentication();
		String role = authuser.getAuthorities().toArray()[0].toString();
		switch (role) {
		case "ADMIN":
			return "/dashboards/admindashboard";
		case "TEACHER":
			return "/dashboards/teacherdashboard";
		case "STUDENT":
			return "/dashboards/studentdashboard";
		case "PARENT":
			return "/dashboards/parentdashboard";
		default:
			return "redirect:login";
		}

	}
	
	
	@PostMapping("/admin/savestudent")
	public String saveStudent(@ModelAttribute Student newstudent, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:dashboard";
		}
		try {
			studentService.saveStudent(newstudent);
		} catch (Exception e) {
			
		}
		return "redirect:dashboard";
		
	}

}
