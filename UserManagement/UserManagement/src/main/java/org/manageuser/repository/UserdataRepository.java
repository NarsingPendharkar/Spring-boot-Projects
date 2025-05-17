package org.manageuser.repository;

import java.util.Optional;

import org.manageuser.entity.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdataRepository extends JpaRepository<Userdata, Long>{
	public boolean existsByEmail(String email);

	public Optional<Userdata> findByEmail(String email);
}
