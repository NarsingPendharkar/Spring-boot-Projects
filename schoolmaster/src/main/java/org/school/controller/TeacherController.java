package org.school.controller;

import java.util.List;

import org.school.DTO.StudentCourseDTO;
import org.school.DTO.TeacherResponse;
import org.school.service.PreloadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher/")
public class TeacherController {
	
	@Autowired PreloadDataService dataService;
	
	  // save teacher
	
	@GetMapping("/teachers")
	@ResponseBody
	public List<TeacherResponse> getlist() {
		return dataService.fetchTeachers();
	}
	  
	 
	 


}
