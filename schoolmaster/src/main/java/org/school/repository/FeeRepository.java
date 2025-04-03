package org.school.repository;

import java.util.Optional;

import org.school.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

	public Optional<Fee> findById(Long id);

	public Optional<Fee> findByStudentId(Long studentId);

	//public Optional<Fee> findByStudentIdAndStatus(Long studentId, String status);

}
