package com.buy.service;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.buy.entity.Personaldetails;

public interface UserService extends UserDetailsService {
    Personaldetails createUser(Personaldetails user);
    Personaldetails getUserById(Integer id);
    List<Personaldetails> getAllUsers();
    Personaldetails updateUser(Integer id, Personaldetails user);
    void deleteUser(Integer id);
}