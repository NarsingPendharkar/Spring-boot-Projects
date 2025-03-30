package org.school.repository;

import java.util.Optional;

import org.school.DTO.TeacherDTO;
import org.school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Optional<Teacher> findByUserId(long userId);

	void save(TeacherDTO teacherDetails);
	void deleteByUserId(long id);
}
