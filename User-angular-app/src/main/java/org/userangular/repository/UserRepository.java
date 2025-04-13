package org.userangular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userangular.entity.Userdata;

public interface UserRepository extends JpaRepository<Userdata, Long> {
	
	Optional<Userdata> findByUsername(String username);

}
