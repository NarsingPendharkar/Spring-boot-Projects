package org.userangular.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.userangular.entity.Userdata;
import org.userangular.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired private UserRepository authRepository;
	

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
