package org.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Schoolcontroller {
	
	@GetMapping("/")
	public String getMethodName() {
		return "dashboard";
	}
	

}
