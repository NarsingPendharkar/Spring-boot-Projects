package com.School360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School360.model.Userdata;

@Repository
public interface UserRepository extends JpaRepository<Userdata, Integer>{

	public Userdata findByUsername(String username);
}
