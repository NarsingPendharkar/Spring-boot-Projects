package org.userangular.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.userangular.config.JwtUtils;
import org.userangular.entity.Userdata;
import org.userangular.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	Logger logger=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			String token = jwtUtils.generateToken(userDetails);

			return ResponseEntity
					.ok(Map.of("token", token, "role", userDetails.getAuthorities().iterator().next().getAuthority()));
		} catch (AuthenticationException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@Autowired
	private UserService authService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	@ResponseBody
	public String userEndpoint() {
		return "Hello, User!";
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody Userdata newUser) {
		try {
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			authService.saveUser(newUser);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("User saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error :" + e.getMessage());
		}

	}

	/*
	 * @GetMapping("/admin/home") public String adminauthenticate() {
	 * logger.info("admin method called !"); Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); return "Hello, " +
	 * authentication.getName().toUpperCase() + ": " +
	 * authentication.getAuthorities() + "! Welcome to the admin section."; }
	 */
	
    @GetMapping("/admin/home")
    public Map<String, Object> adminAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Admin method called!");
        response.put("username", authentication.getName().toUpperCase());
        response.put("roles", authentication.getAuthorities());
        response.put("welcomeMessage", "Welcome to the admin section.");

        return response;
    }

	@GetMapping("/user/home")
	public String userauthenticate() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "Hello, " + authentication.getName().toUpperCase() + ": " + authentication.getAuthorities()
				+ "! Welcome to the admin section.";
	}

}
