package org.umanage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.umanage.entity.AppUser;

@Repository
public interface UserdataRepository extends JpaRepository<AppUser, Long>{
	public boolean existsByEmail(String email);

	public Optional<AppUser> findByEmail(String email);
}
