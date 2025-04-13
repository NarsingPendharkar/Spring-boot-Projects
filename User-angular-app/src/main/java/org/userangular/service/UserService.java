package org.userangular.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.userangular.entity.Userdata;

public interface UserService extends UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;
	
	void saveUser(Userdata newUser) throws UsernameNotFoundException;

}
