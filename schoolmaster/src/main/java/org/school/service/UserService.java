package org.school.service;

import java.util.Collections;

import org.school.model.User;
import org.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private  UserRepository userRepositor;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundUser=userRepositor.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(foundUser.getUsername(),
				foundUser.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(foundUser.getRole()))
				);
	}
	
	public void save(User newUser) {
		userRepositor.save(newUser);
	}
	
	
	
}
