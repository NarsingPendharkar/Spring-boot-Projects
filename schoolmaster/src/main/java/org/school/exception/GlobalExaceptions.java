package org.school.exception;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.UserDTO;
import org.school.entity.Teacher;
import org.school.entity.User;
import org.school.service.TeacherService;
import org.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExaceptions {
	@Autowired
	TeacherService teacherService;
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;
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
	
	
	// Fetch Teacher List
    @ModelAttribute("teacherlist")
    public List<Teacher> fetchTeachers() {
        List<Teacher> teachers =teacherService.findAllTeachers();
        return teachers;
    }

	@ExceptionHandler(TeacherAlreadyPresent.class)
	@ResponseBody
	public String teacherAlreadyPresentException(TeacherAlreadyPresent exception) {
		return "<html><body>"
                + "<h3 style='color:red;'>"+exception.getMessage()+"</h3>"
                + "<script>"
                + "setTimeout(function(){ window.location.href='/dashboards'; }, 3000);"
                + "</script>"
                + "</body></html>";
	}

}
