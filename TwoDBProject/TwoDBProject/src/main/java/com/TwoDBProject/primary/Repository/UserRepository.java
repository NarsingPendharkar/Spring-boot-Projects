package com.TwoDBProject.primary.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TwoDBProject.primary.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

}
