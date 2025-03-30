package org.school.repository;

import java.util.List;
import java.util.Optional;

import org.school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	
	@Query(value = "select * from user where role=?1" , nativeQuery = true)	
	Optional<List<User>>findByRole(String role);

	Optional<User> findById(Long userId);

	@Modifying
	Optional<User> deleteById(long id);
}
