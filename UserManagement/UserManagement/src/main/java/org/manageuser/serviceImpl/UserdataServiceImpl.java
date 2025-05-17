package org.manageuser.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.manageuser.entity.Userdata;
import org.manageuser.exception.UserNotFoundException;
import org.manageuser.repository.UserdataRepository;
import org.manageuser.service.UserdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UserdataServiceImpl implements UserdataService {

   
	@Autowired UserdataRepository userRepository;
	@Autowired  BCryptPasswordEncoder passwordEncoder;

  

	@Override
	public Userdata register(@Valid Userdata newUser) {
		return userRepository.save(newUser);
	}

	@Override
	public Userdata updateUser(@Valid Userdata update, Long id) {
		if(userRepository.existsById(id)) {
			userRepository.save(update);
			return update;
		}
		return null;
		
	}


	@Override
	public List<Userdata> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		Userdata user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Unable to delete. User not found with id: "+id));
		if(user!=null) {
			userRepository.deleteById(id);
		}	
	}


	public Userdata getUserById(Long id) {
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
		Userdata userdata=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User doesn't exists !"));
		User found=new User(userdata.getEmail(), userdata.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(userdata.getRoles())));
		return found;
	}

	



	

	
}
