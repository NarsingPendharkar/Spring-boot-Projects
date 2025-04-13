package org.secure.repository;

import java.util.Optional;

import org.secure.entity.Personaldetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personaldetails, Long> {
	
	Optional<Personaldetails> findByUsername(String username);

}
