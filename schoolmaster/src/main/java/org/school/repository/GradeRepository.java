package org.school.repository;

import java.util.List;

import org.school.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Long>{

	public List<Grade> findByStudentId(Long id);
}
