package org.school.repository;

import java.util.List;
import java.util.Optional;

import org.school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	
	Optional<List<User>>findByRole(String role);
}
