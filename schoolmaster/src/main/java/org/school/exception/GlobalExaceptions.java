package org.school.exception;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.school.DTO.UserDTO;
import org.school.entity.Course;
import org.school.entity.User;
import org.school.service.CourseService;
import org.school.service.StudentService;
import org.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExaceptions {

	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	ModelMapper modelMapper;
	 @ModelAttribute("users")
	    public List<UserDTO> findAllUsers() {
	        List<User> users = userService.findAllUsers();
	        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
	    }
	
	 // Fetch Student List
    @ModelAttribute("studentlist")
    public List<UserDTO> fetchStudents() {
        List<User> students = userService.findUserByRole("STUDENT");
        return modelMapper.map(students, new TypeToken<List<UserDTO>>() {}.getType());
    }
	
    // fetch teacher list
    @ModelAttribute("teacherList")
    public List<UserDTO> fetchTeachers() {
        List<User> teachers = userService.findUserByRole("TEACHER");
        System.out.println(teachers.toString());
        return modelMapper.map(teachers, new TypeToken<List<UserDTO>>() {}.getType());
    }
	
    //course list
   @ModelAttribute("courseList")
   public List<Course> getCourseList() {
	   List<Course> allCourseList = courseService.findAllCourses();
	   System.out.println(allCourseList.toString());
	   return allCourseList;
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
