package com.buy.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy.entity.Personaldetails;

@Repository
public interface UserRepository extends JpaRepository<Personaldetails, Integer> {
   Optional<Personaldetails>findByUsername(String username);
}