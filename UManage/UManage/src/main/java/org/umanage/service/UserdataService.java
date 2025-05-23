package org.umanage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.umanage.dto.AppUserRequest;
import org.umanage.entity.AppUser;

import jakarta.validation.Valid;

public interface UserdataService extends UserDetailsService {
	
	//create
	public AppUser register(@Valid AppUser newUser);
	
	//delete
	public void deleteUser(Long id);
	
	//list
	public List<AppUser> getAllUsers();
	
	public boolean emailExist(String email);
	public AppUser updateUser(@Valid AppUser update, Long id);

	public AppUser getUserById(Long id);

	
	
}
