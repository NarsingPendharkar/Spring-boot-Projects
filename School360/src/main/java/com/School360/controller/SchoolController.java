package com.School360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.School360.model.Userdata;
import com.School360.service.Userdataservice;

@Controller
public class SchoolController {
	@Autowired
	private Userdataservice userdataservice;

	@RequestMapping("/home")
	public String home() {
		return "dashboard";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam String username, @RequestParam String password, Model model) {
		Userdata validate = userdataservice.validate(username);
		if (validate != null && validate.getUsername().equals(username) && validate.getPassword().equals(password)) {
			model.addAttribute("a", validate);
			return "successhome";
		}
		return "redirect:login";
	}
	@RequestMapping("/newadd")
	public String newadd() {
		return "newaddmission";
	}

}
