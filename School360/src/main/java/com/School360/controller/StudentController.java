package com.School360.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.School360.model.Student;
import com.School360.service.StudentService;

@Controller

public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/admit")
	public String admit(@ModelAttribute Student student) {
		studentService.admitstd(student);
		return "redirect:allstd";
	}
	@GetMapping("/allstd")
	public String getall(Model model){
		List<Student> allstd=studentService.getallstd();
		model.addAttribute("std", allstd);
		return "Studentlist";

	}
}
