package org.security.service;

import org.security.entity.Userdata;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService extends UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;
	
	void saveUser(Userdata newUser) throws UsernameNotFoundException;

}
