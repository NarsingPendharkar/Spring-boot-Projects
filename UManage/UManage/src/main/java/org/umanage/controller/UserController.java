package org.umanage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@GetMapping("/user")
	public String user() {
		return "Welcome user";
	}

	//view profile and edit profile
	//change password using otp
	//upload photo
	
	
	
}
