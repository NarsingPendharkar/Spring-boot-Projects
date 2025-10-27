package org.security.controller;

import org.security.entity.Userdata;
import org.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AuthController {
	
	@Autowired private AuthService authService;
	
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody Userdata newUser) {
		try {
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			authService.saveUser(newUser);
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User saved successfully");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error :"+e.getMessage());
		}
		
    }
	
	@GetMapping("/admin/home")
	public String adminauthenticate() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "Hello, " + authentication.getName().toUpperCase() +": "+authentication.getAuthorities()+ "! Welcome to the admin section.";
    }
	
	@GetMapping("/user/home")
	public String userauthenticate() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "Hello, " + authentication.getName().toUpperCase() +": "+authentication.getAuthorities()+ "! Welcome to the admin section.";
    }
	
	@GetMapping("/home")
	public String getMethodName(@RequestParam String param) {
		return "User is valid !";
	}
	
	

}
