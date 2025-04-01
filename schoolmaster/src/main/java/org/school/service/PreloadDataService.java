package org.school.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.UserDTO;
import org.school.entity.Course;
import org.school.entity.Fee;
import org.school.entity.Grade;
import org.school.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class PreloadDataService {
	
	@Autowired
	UserService userService;
	@Autowired
	GradeService gradeService;
	@Autowired
	CourseService courseService;
	@Autowired
	FeeService feeService;
	@Autowired
	ModelMapper modelMapper;
	 @Cacheable("users")
	    public List<UserDTO> findAllUsers() {
	        List<User> users = userService.findAllUsers();
	        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
	    }
	
	 // Fetch Student List
    @Cacheable("studentlist")
    public List<UserDTO> fetchStudents() {
        List<User> students = userService.findUserByRole("STUDENT");
        return modelMapper.map(students, new TypeToken<List<UserDTO>>() {}.getType());
    }
	
    // fetch teacher list
    @Cacheable("teacherList")
    public List<UserDTO> fetchTeachers() {
        List<User> teachers = userService.findUserByRole("TEACHER");
        return modelMapper.map(teachers, new TypeToken<List<UserDTO>>() {}.getType());
    }
	
    //course list
   @Cacheable("courseList")
   public List<Course> getCourseList() {
	   List<Course> allCourseList = courseService.findAllCourses();
	   return allCourseList;
   }
   // grade list
   @Cacheable("gradeList")
   public List<Grade> getGradeList() {
       List<Grade> gradeList = gradeService.getAllGrades();
       return gradeList;
   }
   
   // fee list
   @Cacheable("feeList")
   public List<Fee> getFeeList() {
       List<Fee> feeList =feeService.findAllFees();
       return feeList;
   }
   
	/*
	 * // student fee list
	 * 
	 * @Cacheable("studentFeeList") public List<Grade> getStudentFeeList(Long
	 * studentId) { List<Grade> studentFeeList =
	 * gradeService.getStudentGrades(studentId); return studentFeeList; }
	 */
   


}
