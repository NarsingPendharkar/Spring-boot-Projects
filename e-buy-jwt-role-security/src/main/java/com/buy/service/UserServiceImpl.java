package com.buy.service;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.buy.entity.Personaldetails;
import com.buy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Personaldetails createUser(Personaldetails user) {
        return userRepository.save(user);
    }

    @Override
    public Personaldetails getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<Personaldetails> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Personaldetails updateUser(Integer id, Personaldetails updatedUser) {
        Personaldetails existing = getUserById(id);
        existing.setUsername(updatedUser.getUsername());
        existing.setPassword(updatedUser.getPassword());
        existing.setRole(updatedUser.getRole());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personaldetails user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Personaldetails not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(() -> "ROLE_" + user.getRole().name())
        );
}}