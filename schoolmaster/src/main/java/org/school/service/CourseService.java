package org.school.service;

import java.util.List;

import org.school.entity.Course;
import org.school.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	// find course by id 
	public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
	// delete by id
	public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
	//update course by id
	public void updateCourse(Course course) {
        courseRepository.save(course);
    }
	// course list
	public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
	public void saveCourse(Course course) {
		 courseRepository.save(course);
		
	}
	
	

}
