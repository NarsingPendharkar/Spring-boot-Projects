package org.school.service;

import org.school.entity.Teacher;
import org.school.repository.StudentRepository;
import org.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
	//delete teacher
	public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
	
	

}
