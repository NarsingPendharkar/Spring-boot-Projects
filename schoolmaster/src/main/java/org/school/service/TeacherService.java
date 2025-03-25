package org.school.service;

import java.util.List;

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
	
	// find all teacher
	public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }
	//delete teacher
	public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
	
	

}
