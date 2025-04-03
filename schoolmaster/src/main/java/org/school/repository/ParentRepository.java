package org.school.repository;

import java.util.Optional;

import org.school.entity.ParentDetails;
import org.school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<ParentDetails, Long> {
	
	Optional<ParentDetails> findById(long id);

	Optional<ParentDetails> findByUser(User user);

}
