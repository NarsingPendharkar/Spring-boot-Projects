package org.school.service;

import java.util.Optional;

import org.school.entity.ParentDetails;
import org.school.entity.User;
import org.school.repository.ParentRepository;
import org.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ParentsService {

	@Autowired
	
	private ParentRepository parentRepository;


	public void saveParent(ParentDetails parents) {
		parentRepository.save(parents);
	}
	
	public Optional<ParentDetails>findParentsById(long id){
		return parentRepository.findById(id);
	}
	
	@Service
	public class ParentService {

	    @Autowired
	    private ParentRepository parentRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Transactional
	    public ParentDetails saveParentDetails(Long userId, String contactNumber) {
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        
	        if (!"PARENT".equalsIgnoreCase(user.getRole())) {
	            throw new RuntimeException("User is not a parent");
	        }

	        ParentDetails parent = parentRepository.findByUser(user)
	                .orElse(new ParentDetails());
			/*
			 * parent.setUser(user); parent.setContactNumber(contactNumber);
			 */

	        return parentRepository.save(parent);
	    }
	}



}
