package org.school.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.school.DTO.CourseDTO;
import org.school.DTO.GradeDTO;
import org.school.DTO.StudentCourseDTO;
import org.school.DTO.TeacherRequest;
import org.school.entity.Attendance;
import org.school.entity.Course;
import org.school.entity.Fee;
import org.school.entity.Grade;
import org.school.entity.Student;
import org.school.entity.User;
import org.school.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller

public class Admincontroller {

    

	private final Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ParentsService parentsService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private FeeService feeService;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private PreloadDataService dataService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	
	@Autowired TeacherService teacherService;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    Admincontroller(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

	@GetMapping("/login")
	public String loginPage() {
		return "/user/login";
	}

	@GetMapping("/logout")
	public String logoutPage() {

		return "redirect:login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "/user/register";
	}

	@PostMapping("/saveuser")
	public String saveUser(@ModelAttribute User newuser) {
		String passwordcode = newuser.getPassword();
		newuser.setPassword(passwordEncoder.encode(passwordcode));
		userService.save(newuser);
		return "redirect:login";
	}

	@PostMapping("/adminadduser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> adminAdduser(@ModelAttribute User newuser) {
		String passwordcode = newuser.getPassword();
		newuser.setPassword(passwordEncoder.encode(passwordcode));
		userService.save(newuser);
		System.out.println(newuser.toString());
		return ResponseEntity.ok("<html><body><h3>User created successfully !</h3></body> \r\n" + "<script>\r\n"
				+ "    setTimeout(() => {\r\n" + "        window.location.href=\"/dashboards\";\r\n"
				+ "    }, 2000);\r\n" + "</script></html>");
	}

	@GetMapping("/dashboards")
	public String dashboardPage(Model model) {
		Authentication authuser = SecurityContextHolder.getContext().getAuthentication();
		// String role = authuser.getAuthorities().toArray()[0].toString();
		String role = authuser.getAuthorities().iterator().next().getAuthority();
		User loggedUser = userService.findByUsername(authuser.getName());
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("users", dataService.findAllUsers());
		model.addAttribute("studentlist", dataService.fetchStudents());
		model.addAttribute("teacherList", dataService.fetchTeachers());
		model.addAttribute("courseList", dataService.getCourseList());
		model.addAttribute("gradeList", dataService.getGradeList());
		model.addAttribute("feeList", dataService.getFeeList());
		model.addAttribute("role", role);
		if (role.equalsIgnoreCase("ADMIN")) {

			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("TEACHER")) {

			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("STUDENT")) {
			model.addAttribute("studentGrades", gradeService.getStudentsGrades(loggedUser.getId()));
			model.addAttribute("attendanceRecords", attendanceService.studentAttendance(loggedUser.getId()));
			model.addAttribute("enrollmentofstudent", dataService.studentEnrollment(loggedUser.getId()));
			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("PARENT")) {

			return "/dashboards/admindashboard";
		} else {
			return "redirect:/login";
		}

	}
	
	
	@PostMapping("/saveteacher")
	@CacheEvict(value =  "teacherList" , allEntries = true)
	public ResponseEntity<String> newTeacher(@ModelAttribute TeacherRequest request){
		try {
			String passwordcode = request.getPassword();
			request.setPassword(passwordEncoder.encode(passwordcode));
			teacherService.addNewTeacher(request);
			return ResponseEntity.ok("Teacher added !");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error :"+e.getMessage());
		}
		
		
	}
	
	/*
	 * @PreAuthorize("hasAuthority('ADMIN')")
	 * 
	 * @PostMapping("/saveteacher") public String saveTeacher(@ModelAttribute
	 * Teacher newteacher, BindingResult result, RedirectAttributes flash) throws
	 * TeacherAlreadyPresent{ if (result.hasErrors()) {
	 * flash.addFlashAttribute("message", "Invalid teacher description");
	 * logger.error("Invalid teacher description"); return "redirect:/dashboards"; }
	 * try { newteacher.setEmployeeId("Emp_"+newteacher.getEmployeeId());
	 * teacherService.saveTeacher(newteacher); flash.addFlashAttribute("message",
	 * "Teacher saved successfully"); logger.info("Teacher saved successfully");
	 * return "redirect:/dashboards"; } catch (Exception e) {
	 * flash.addFlashAttribute("message", "Error saving teacher"); throw new
	 * TeacherAlreadyPresent("Teacher already present"); } }
	 */

	
	/*
	 * @GetMapping("/userdata")
	 * 
	 * @ResponseBody public UserdataDTO getMethodName(Model model) { String url =
	 * "https://dummyjson.com/users/1"; UserdataDTO res =
	 * restTemplate.getForObject(url, UserdataDTO.class); return res; }
	 */

	// delete user by id
	@GetMapping("deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUserbyId(id);
		return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>User deleted successfully !</h3>\r\n"
				+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
				+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 2000);\r\n" + "</script>\r\n"
				+ "</html>");
	}

	// save course
	@PostMapping("/addcourse")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addCoursePage(Model model, @ModelAttribute CourseDTO courseDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			logger.error("Invalid course description");
			return ResponseEntity.badRequest().body("Invalid course description");
		}
		Course newcourse = modelMapper.map(courseDTO, Course.class);
		User teacher = userService.findUserByid(courseDTO.getTeacherId());
		newcourse.setTeacher(teacher);
		courseService.saveCourse(newcourse);
		return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>Course added successfully !</h3>\r\n"
				+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
				+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 2000);\r\n" + "</script>\r\n"
				+ "</html>");
	}

	// delete course
	@GetMapping("deletecourse/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
		try {
			courseService.deleteById(id);
			return ResponseEntity.ok("course successfully deleted");
		} catch (Exception e) {
			return ResponseEntity.ok("Please try again later");
		}
	}

	// add new grade

	@PostMapping("/addgrade")
	public ResponseEntity<String> addGrade(@ModelAttribute GradeDTO newGrade, Model model) {
		
		try {
			Student student=studentService.findById(newGrade.getStudentId());
			Course course=courseService.findById(newGrade.getCourseId());
			logger.error(student.toString());
			logger.error(course.toString());
			if(student==null || course==null ) {
				throw new IllegalArgumentException("Teacher or Grade not found !");
			}
			Grade addGrade=modelMapper.map(newGrade, Grade.class);
			addGrade.setStudent(student);
			addGrade.setCourse(course);
			System.out.println(addGrade.toString());
			gradeService.saveGrade(addGrade);
			return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>Greade added successfully !</h3>\r\n"
					+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
					+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 2000);\r\n" + "</script>\r\n"
					+ "</html>");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error"+e.getMessage());
		}
		
	}

	// add new fee

	@PostMapping("/addfee")
	public ResponseEntity<String> addFee(Model model, @ModelAttribute Fee fee) {

		System.out.println(fee.toString());
		feeService.saveFee(fee);
		return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>Fee added successfully !</h3>\r\n"
				+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
				+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 2000);\r\n" + "</script>\r\n"
				+ "</html>");
	}

	@PostMapping("/addattendance")
	public ResponseEntity<String> markAttendance(@ModelAttribute Attendance attendance) {
		try {
			attendanceService.markAttendance(attendance);
			return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>Attendance marked successfully !</h3>\r\n"
					+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
					+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 1000);\r\n" + "</script>\r\n"
					+ "</html>");
		} catch (Exception e) {
			return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>Error occured please try again !</h3>\r\n"
					+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
					+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 1000);\r\n" + "</script>\r\n"
					+ "</html>");
		}

	}

	@GetMapping("/stdcourses/{id}")
	@ResponseBody
	public List<StudentCourseDTO> getlist(@PathVariable long id) {
		return dataService.getStudentCourses(id);
	}
	
	

}