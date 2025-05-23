package org.umanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.umanage.entity.UserProfile;
import org.umanage.serviceImpl.UserProfileServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "User related endpoints")

public class UserController {
	
	@Autowired private UserProfileServiceImpl profileService;
	
	@GetMapping("/user")
	public String user() {
		return "Welcome user";
	}

	//view profile and edit profile
	//change password using otp
	//upload photo
	
	@GetMapping("list")
	public List<UserProfile> getAllUserProfiles(){
		return profileService.getListOfProfiles();	
	}

	@PostMapping("add/{id}")
	public UserProfile saveProfile(@RequestBody @Valid UserProfile newProfile,@PathVariable("id") Long userId){
		return profileService.addUserProfile(newProfile,userId);	
	}
	
}
