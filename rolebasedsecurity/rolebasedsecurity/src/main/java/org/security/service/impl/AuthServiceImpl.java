package org.security.service.impl;

import java.util.Collections;

import org.security.entity.Userdata;
import org.security.repository.AuthRepository;
import org.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired private AuthRepository authRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userdata user=authRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       User data=new User(user.getUsername(), user.getPassword(),Collections.singletonList(new SimpleGrantedAuthority(user.getRoles())));
		return data;
	}

	@Override
	public void saveUser(Userdata newUser) throws UsernameNotFoundException {
		authRepository.save(newUser);
	}
	
	

}
