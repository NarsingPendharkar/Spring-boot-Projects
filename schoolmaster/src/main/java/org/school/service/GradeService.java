package org.school.service;

import java.util.List;

import org.school.entity.Grade;
import org.school.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {
	
	@Autowired
	private GradeRepository gradeRepository;
	
	// save grade
	public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }
	// get all grades
	public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
	//delete grade
	public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
	//get grade by id
	public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }
	// get grade by student id
	public List<Grade> getStudentsGrades(Long StudentId){
		return gradeRepository.findByStudentId(StudentId);
	}

}
