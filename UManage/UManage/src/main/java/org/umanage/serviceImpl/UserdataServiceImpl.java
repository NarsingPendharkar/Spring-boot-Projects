package org.umanage.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.umanage.entity.AppUser;
import org.umanage.exception.UserNotFoundException;
import org.umanage.repository.UserdataRepository;
import org.umanage.service.UserdataService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UserdataServiceImpl implements UserdataService {

   
	@Autowired UserdataRepository userRepository;
	@Autowired  BCryptPasswordEncoder passwordEncoder;

  

	@Override
	public AppUser register(@Valid AppUser newUser) {
		return userRepository.save(newUser);
	}

	@Override
	public AppUser updateUser(@Valid AppUser update, Long id) {
		if(userRepository.existsById(id)) {
			userRepository.save(update);
			return update;
		}
		return null;
		
	}


	@Override
	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		AppUser user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Unable to delete. User not found with id: "+id));
		if(user!=null) {
			userRepository.deleteById(id);
		}	
	}


	public AppUser getUserById(Long id) {
	    return userRepository.findById(id)
	                         .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
	}

	@Override
	public boolean emailExist(String email) {
		if(userRepository.existsByEmail(email)) {
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser userdata=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User doesn't exists !"));
		User found=new User(userdata.getEmail(), userdata.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(userdata.getRoles())));
		return found;
	}

	



	

	
}
