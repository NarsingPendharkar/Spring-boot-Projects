package org.manageuser.service;

import java.util.List;
import java.util.Optional;

import org.manageuser.entity.Userdata;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.validation.Valid;

public interface UserdataService extends UserDetailsService {
	
	//create
	public Userdata register(@Valid Userdata newUser);
	
	//delete
	public void deleteUser(Long id);
	
	//list
	public List<Userdata> getAllUsers();
	
	public boolean emailExist(String email);
	public Userdata updateUser(@Valid Userdata update, Long id);

	public Userdata getUserById(Long id);

	
	
}
