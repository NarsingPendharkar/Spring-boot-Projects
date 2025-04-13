package org.secure.service;

import java.util.Collections;

import org.secure.entity.Personaldetails;
import org.secure.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonalService implements UserDetailsService  {

	@Autowired
    private  PersonalRepository personalRepository;

    

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Personaldetails personaldetails =personalRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(" Username not found " + username));
		User securityUser=new User(personaldetails.getUsername(), 
				personaldetails.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(personaldetails.getRoles())));  
		return securityUser;
	}
	
	public void saveUser(Personaldetails newUser) throws UsernameNotFoundException {
		personalRepository.save(newUser);
	}

}
