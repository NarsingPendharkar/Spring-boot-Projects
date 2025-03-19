package com.School360.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.School360.model.Userdata;
import com.School360.repository.UserRepository;

@Service
public class Userdataservice {
	@Autowired
	private UserRepository userRepository;

	public Userdata validate(String username) {
		return userRepository.findByUsername(username);
	}

}
