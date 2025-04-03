package org.school.repository;

import java.util.Optional;

import org.school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Optional<Teacher> findById(long id);

}
