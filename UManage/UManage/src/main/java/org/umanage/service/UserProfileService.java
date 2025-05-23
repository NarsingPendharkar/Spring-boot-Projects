package org.umanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.umanage.entity.UserProfile;

import jakarta.validation.Valid;


public interface UserProfileService {
	
	public UserProfile updateUserProfile(UserProfile updatedprofile,Long userId);
	public List<UserProfile> getListOfProfiles();
	UserProfile addUserProfile(@Valid UserProfile userProfile, Long id);

}
