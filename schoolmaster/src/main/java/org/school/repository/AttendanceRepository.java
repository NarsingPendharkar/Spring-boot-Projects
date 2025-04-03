package org.school.repository;

import java.util.List;

import org.school.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	public List<Attendance> findByStudentId(Long id);

}
