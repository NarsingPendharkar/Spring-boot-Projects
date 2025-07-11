package com.security.InMemoryAuth.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.InMemoryAuth.Dao.UserRepo;
import com.security.InMemoryAuth.Entity.User;

@Service
public class UserService implements UserDetailsService {
	
	 @Autowired
	    private UserRepo userRepository;
	    
	    public void saveUser(User user) {
	    	userRepository.save(user);
	    }

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user=userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
			return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
	        );
		}




		public void save(User newUser) {
			userRepository.save(newUser);
			
		}

}
