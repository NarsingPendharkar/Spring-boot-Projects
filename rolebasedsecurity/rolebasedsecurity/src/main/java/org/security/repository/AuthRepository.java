package org.security.repository;

import java.util.Optional;

import org.security.entity.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Userdata, Long> {
	
	Optional<Userdata> findByUsername(String username);

}
