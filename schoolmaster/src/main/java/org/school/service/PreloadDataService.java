package org.school.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.CourseDTO;
import org.school.DTO.GradeDTO;
import org.school.DTO.StudentCourseDTO;
import org.school.DTO.TeacherResponse;
import org.school.DTO.UserDTO;
import org.school.controller.Studentcontroller;
import org.school.entity.Course;
import org.school.entity.Enrollment;
import org.school.entity.Fee;
import org.school.entity.Grade;
import org.school.entity.Teacher;
import org.school.entity.User;
import org.school.repository.EnrollmentRepository;
import org.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class PreloadDataService {

	private final StudentRepository studentRepository;

	private final Studentcontroller studentcontroller;

	@Autowired
	private UserService userService;
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private CourseService courseService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	private FeeService feeService;
	@Autowired
	private ModelMapper modelMapper;

	PreloadDataService(Studentcontroller studentcontroller, StudentRepository studentRepository) {
		this.studentcontroller = studentcontroller;
		this.studentRepository = studentRepository;
	}

	@Cacheable("users")
	public List<UserDTO> findAllUsers() {
		List<User> users = userService.findAllUsers();
		return modelMapper.map(users, new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	// Fetch Student List
	@Cacheable("studentlist")
	public List<UserDTO> fetchStudents() {
		List<User> students = userService.findUserByRole("STUDENT");
		return modelMapper.map(students, new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	// fetch teacher list
	@Cacheable("teacherList")
	public List<TeacherResponse> fetchTeachers() {
		List<Teacher> teachers = teacherService.getAllTeachers();
		List<TeacherResponse> response = teachers.stream().map((tea) -> new TeacherResponse(tea.getId(), tea.getUser().getUsername(),
				 tea.getFirstName(),	tea.getLastName(), tea.getSubject()))
				.collect(Collectors.toList());
		return response;
	}

	// fetch parent list
	@Cacheable("parentList")
	public List<UserDTO> fetchParents() {
		List<User> teachers = userService.findUserByRole("PARENT");
		return modelMapper.map(teachers, new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	// course list
	@Cacheable("courseList")
	public List<CourseDTO> getCourseList() {
		List<Course> allCourseList = courseService.findAllCourses();
		List<CourseDTO> listofcourses = modelMapper.map(allCourseList, new TypeToken<List<CourseDTO>>() {
		}.getType());
		return listofcourses;
	}

	// grade list
	@Cacheable("gradeList")
	public List<GradeDTO> getGradeList() {
		List<Grade> gradeList = gradeService.getAllGrades();
		List<GradeDTO> gradeListDTO = modelMapper.map(gradeList, new TypeToken<List<GradeDTO>>() {
		}.getType());
		return gradeListDTO;
	}

	// fee list
	@Cacheable("feeList")
	public List<Fee> getFeeList() {
		List<Fee> feeList = feeService.findAllFees();
		return feeList;
	}

	@Cacheable("enrollmentofstudent")
	public List<Enrollment> studentEnrollment(long studentId) {
		return enrollmentRepository.findByStudentId(studentId);
	}

	@Cacheable("studentcourses")
	public List<StudentCourseDTO> getStudentCourses(Long studentId) {
		List<Object[]> results = enrollmentRepository.getStudentEnrolledCourses(studentId);

		return results.stream().map(obj -> new StudentCourseDTO((String) obj[0], // courseName
				(String) obj[1], // description
				(String) obj[2], // teacherName
				(Long) obj[3], // teacherId
				(Long) obj[4], // courseId
				(Long) obj[5] // studentId
		)).collect(Collectors.toList());
	}
	/*
	 * // student fee list
	 * 
	 * @Cacheable("studentFeeList") public List<Grade> getStudentFeeList(Long
	 * studentId) { List<Grade> studentFeeList =
	 * gradeService.getStudentGrades(studentId); return studentFeeList; }
	 */

}
