package org.school.service;

import java.util.Collections;
import java.util.List;

import org.school.entity.ParentDetails;
import org.school.entity.Student;
import org.school.entity.Teacher;
import org.school.entity.User;
import org.school.repository.ParentRepository;
import org.school.repository.StudentRepository;
import org.school.repository.TeacherRepository;
import org.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private  UserRepository userRepositor;
	
	
	
	  @Autowired private StudentRepository studentRepository;
	  
	  @Autowired private TeacherRepository teacherRepository;
	  
	  @Autowired private ParentRepository parentRepository;
	 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundUser=userRepositor.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(foundUser.getUsername(),
				foundUser.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(foundUser.getRole()))
				);
	}
	
	public User save(User newUser) {
		userRepositor.save(newUser);
		return newUser;
	}
	
	public User saveAndGet(User newUser) {
		return userRepositor.save(newUser);
	}
	
// find user by role
	public List<User> findUserByRole(String role) {
        return userRepositor.findByRole(role).orElse(null);
    }

	public User findUserByid(Long userId) {
		return userRepositor.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
	}

	public void deleteUserbyId(long id) {
		userRepositor.deleteById(id).orElseThrow(()-> new RuntimeException("User not found"));
	}
	
// find all users
	public List<User> findAllUsers() {
        List<User> users = userRepositor.findAll();
        return users;
    }
	
	public User findByUsername(String username) {
		return userRepositor.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
	}
	

	
	
	
}
