package org.school.controller;

import org.modelmapper.ModelMapper;
import org.school.entity.Course;
import org.school.entity.User;
import org.school.service.CourseService;
import org.school.service.StudentService;
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
import org.springframework.web.client.RestTemplate;

@Controller

public class Admincontroller {

	private final Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ModelMapper modelMapper;

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

	@PostMapping("/adminadduser")
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
		model.addAttribute("role", role);
		if (role.equalsIgnoreCase("ADMIN")) {

			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("TEACHER")) {

			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("STUDENT")) {

			return "/dashboards/admindashboard";
		} else if (role.equalsIgnoreCase("PARENT")) {

			return "/dashboards/admindashboard";
		} else {
			return "redirect:/login";
		}

	}

	/*
	 * // save teacher
	 * 
	 * @PreAuthorize("hasAuthority('ADMIN')")
	 * 
	 * @PostMapping("/admin/saveteacher") public String saveTeacher(@ModelAttribute
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
    public ResponseEntity<String> addCoursePage(Model model, @ModelAttribute Course course,BindingResult result) {
		System.out.println(course.toString());
		if(result.hasErrors()) {
			logger.error("Invalid course description");
            return ResponseEntity.badRequest().body("Invalid course description");
		}
       courseService.saveCourse(course);
   	return ResponseEntity.ok("<html>\r\n" + "<body>\r\n" + "    <h3>Course added successfully !</h3>\r\n"
			+ "</body> \r\n" + "<script>\r\n" + "    setTimeout(() => {\r\n"
			+ "        window.location.href=\"/dashboards\";\r\n" + "    }, 2000);\r\n" + "</script>\r\n"
			+ "</html>");
    }
	
	

}
