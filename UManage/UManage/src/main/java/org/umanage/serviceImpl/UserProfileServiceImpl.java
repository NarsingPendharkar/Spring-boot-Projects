package org.umanage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.umanage.entity.AppUser;
import org.umanage.entity.UserProfile;
import org.umanage.exception.UserNotFoundException;
import org.umanage.repository.UserProfileRepository;
import org.umanage.service.UserProfileService;

import jakarta.validation.Valid;


@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired private UserProfileRepository profileRepository;
	@Autowired private UserdataServiceImpl userServiceImpl;

	@Override
	public UserProfile addUserProfile(@Valid UserProfile userProfile,Long id) {
		AppUser user=userServiceImpl.getUserById(id);
		userProfile.setUser(user);
		return profileRepository.save(userProfile);
	}

	@Override
	public UserProfile updateUserProfile(UserProfile updatedprofile, Long userId) {
		UserProfile existing=profileRepository.findById(userId)
				.orElseThrow(()->new UserNotFoundException("Unable to update. User not found with id: "+userId));
		if(existing!=null) {
			existing.setAddress(updatedprofile.getAddress());
			existing.setDateOfBirth(updatedprofile.getDateOfBirth());
			existing.setPhone(updatedprofile.getPhone());
			profileRepository.save(existing);
		}
		return null;
	}

	@Override
	public List<UserProfile> getListOfProfiles() {
		return profileRepository.findAll();
	}



	

}
