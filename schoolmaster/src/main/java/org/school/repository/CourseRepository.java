package org.school.repository;

import java.util.Optional;

import org.school.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	public Optional<Course> findById(Long id) ;
	

}
