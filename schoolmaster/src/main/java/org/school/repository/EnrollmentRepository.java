package org.school.repository;

import java.util.List;

import org.school.DTO.StudentCourseDTO;
import org.school.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	public List<Enrollment>findByStudentId(Long id);
	
	@Query(value="SELECT  c.course_name, c.description, u.first_name, c.teacher_id, e.course_id, e.student_id\r\n"
			+ "FROM enrollments e JOIN courses c ON e.course_id = c.id JOIN user u ON c.teacher_id = u.id\r\n"
			+ "WHERE e.student_id = ?1", nativeQuery = true)
	public List<Object[]>getStudentEnrolledCourses(long id);
}
