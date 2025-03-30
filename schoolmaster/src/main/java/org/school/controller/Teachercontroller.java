package org.school.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.TeacherDTO;
import org.school.DTO.UserDTO;
import org.school.entity.Teacher;
import org.school.entity.User;
import org.school.service.ClassEntityService;
import org.school.service.StudentService;
import org.school.service.TeacherService;
import org.school.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class Teachercontroller {
	
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
	
	
	   
    @GetMapping("teacherDetails/{id}")
    public String getMethodName(@PathVariable Long id,Model model) {	
    	Teacher  teacher=teacherService.getTeacherByUserId(id);
    	User user=userService.findUserByid(teacher.getUserId());
    TeacherDTO response  = modelMapper.map(teacherService.getTeacherByUserId(id),TeacherDTO.class);
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    model.addAttribute("teacherdetails", response);
   return "/teacher/teacherDetails";
    }
    
    @PostMapping("/updateTeacherDetails")
    public String putMethodName(@ModelAttribute TeacherDTO teacherDetails, BindingResult result,RedirectAttributes redirectAttributes) {
      if(result.hasErrors()) {
    	  redirectAttributes.addFlashAttribute("message", "Error in updating teacher details.");
    	  return  "redirect:/dashboards";
      }
      try {
    	  System.err.println(teacherDetails.toString());
    	  teacherService.updateTeacher(teacherDetails);
          return "redirect:/dashboards";
	} catch (Exception e) {
		redirectAttributes.addFlashAttribute("message", "Error saving");
		 return "redirect:/dashboards";
	}
       
    }
    
    
    //delete teacher by id
    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        try {
            teacherService.deleteTeacher(id);
            userService.deleteUserbyId(id);
            redirectAttributes.addFlashAttribute("message", "Teacher deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error deleting teacher.");
        }
        return "redirect:/dashboards";
    }
    
    // Fetch Teacher List
    @ModelAttribute("teacherlist")
    public List<Teacher> fetchTeachers() {
        List<Teacher> teachers =teacherService.findAllTeachers();
        return teachers;
    }
    

 
	
	

}
