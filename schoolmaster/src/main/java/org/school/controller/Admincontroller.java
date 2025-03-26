package org.school.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.StudentDTO;
import org.school.DTO.StudentResponseDTO;
import org.school.DTO.UserDTO;
import org.school.DTO.UserdataDTO;
import org.school.entity.ClassEntity;
import org.school.entity.Student;
import org.school.entity.Teacher;
import org.school.entity.User;
import org.school.service.ClassEntityService;
import org.school.service.StudentService;
import org.school.service.TeacherService;
import org.school.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class Admincontroller {
	
	private final Logger logger =LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ClassEntityService classService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
		System.out.println(newuser.toString());
		return "redirect:login";
	}

	@GetMapping("/dashboards")
	public String dashboardPage(Model model) {
		Authentication authuser = SecurityContextHolder.getContext().getAuthentication();
		//String role = authuser.getAuthorities().toArray()[0].toString();
		String  role =authuser.getAuthorities().iterator().next().getAuthority();
		
		if (role.equalsIgnoreCase("ADMIN")) {
			List<Student> allStudents = studentService.findallStudent();
			List<StudentResponseDTO> response = modelMapper.map(allStudents, new TypeToken<List<StudentDTO>>() {}.getType());
			model.addAttribute("allStudents", response);
			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("TEACHER")) {
			// Fetch all students assigned to this teacher from DB and add to model
			// ...
			return "/dashboards/teacherdashboard";
		} else if (role.equalsIgnoreCase("STUDENT")) {
			// Fetch student data and add to model
			// ...
			return "/dashboards/studentdashboard";
		} else if (role.equalsIgnoreCase("PARENT")) {
			// Fetch parent data and add to model
			// ...
			return "/dashboards/parentdashboard";
		} else {
			return "redirect:/login";
		}

	}

	/*
	 * @PostMapping("/admin/savestudent") public ResponseEntity<String>
	 * saveStudent(@ModelAttribute StudentDTO studentDTO) { // Map DTO to Student
	 * (Student already extends User) Student student = modelMapper.map(studentDTO,
	 * Student.class);
	 * 
	 * // Encode password and set role
	 * 
	 * // Fetch ClassEntity from DB instead of mapping directly ClassEntity
	 * userClass = classService.findbyclassname(studentDTO.getClassName());
	 * student.setAssignedClass(userClass);
	 * 
	 * // Save Student (which is also User) studentService.saveStudent(student);
	 * 
	 * return ResponseEntity.ok("Student saved successfully"); }
	 */

	@GetMapping("/fetchstudent")
	@ResponseBody
	public List<StudentDTO> fetchStudent(Model model) {
	    List<Student> students = studentService.findallStudent();
	    // Correct way to map a list of entities to a list of DTOs
	    List<StudentDTO> studentlist = modelMapper.map(students, new TypeToken<List<StudentDTO>>() {}.getType());
	    model.addAttribute("allstudents", studentlist);
	    return studentlist;
	}
	
	// Fetch Teacher List
    @ModelAttribute("teacherlist")
    public List<Teacher> fetchTeachers() {
        List<Teacher> teachers =teacherService.findAllTeachers();
        return teachers;
    }
    // fetch teacher list
 // Fetch Student List
    @ModelAttribute("teacherUser")
    public List<UserDTO> fetchUserTeacher() {
        List<User> teachers = userService.findUserByRole("TEACHER");	
        return modelMapper.map(teachers, new TypeToken<List<UserDTO>>() {}.getType());
    }

    // Fetch Student List
    @ModelAttribute("studentlist")
    public List<UserDTO> fetchStudents() {
        List<User> students = userService.findUserByRole("STUDENT");
        return modelMapper.map(students, new TypeToken<List<UserDTO>>() {}.getType());
    }
    
    // save teacher 
    @PostMapping("/admin/saveteacher")
    public String saveTeacher(@ModelAttribute Teacher newteacher, BindingResult result, RedirectAttributes flash) {
    	if (result.hasErrors()) {
    		flash.addFlashAttribute("message", "Invalid teacher description");
    		logger.error("Invalid teacher description");
            return "redirect:/dashboards";
        }
        try {
        	newteacher.setEmployeeId("Teacher_"+newteacher.getEmployeeId());
			teacherService.saveTeacher(newteacher);
			flash.addFlashAttribute("message", "Teacher saved successfully");
			logger.info("Teacher saved successfully");
		  return "redirect:/dashboards";
		} catch (Exception e) {
			flash.addFlashAttribute("message", "Error saving teacher");
			logger.error("Error saving teacher", e);
			  return "redirect:/dashboards";
		}
}
    
    @GetMapping("/userdata")
    @ResponseBody
    public UserdataDTO getMethodName(Model model) {
    	String url = "https://dummyjson.com/users/1";
    UserdataDTO res =	restTemplate.getForObject(url, UserdataDTO.class);
        return res;
    }
    
}
