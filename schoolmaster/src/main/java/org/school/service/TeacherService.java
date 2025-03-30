package org.school.service;

import java.util.List;

import org.school.DTO.TeacherDTO;
import org.school.entity.Teacher;
import org.school.repository.StudentRepository;
import org.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	
	//saver teacher
	public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
	//get teacher by id
	public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }
	
	// get teacher by userid
	public Teacher getTeacherByUserId(Long userId) {
        return teacherRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("Teacher not found"));
    }
	// find all teacher
	public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }
	//delete teacher
	public void deleteTeacher(Long id) {
        teacherRepository.deleteByUserId(id);
    }
	public void updateTeacher(TeacherDTO teacherDetails) {
		Teacher teacher=new Teacher();
		teacher.setId(teacherDetails.getId());
		teacher.setUserId(teacherDetails.getUserId());
		teacher.setEmployeeId(teacherDetails.getEmployeeId());
		teacher.setSubjectSpecialization(teacherDetails.getSubjectSpecialization());
		teacherRepository.save(teacher);
		
	}
	
	

}
