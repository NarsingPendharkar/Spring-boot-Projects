package org.school.service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.school.DTO.TeacherRequest;
import org.school.entity.Teacher;
import org.school.entity.User;
import org.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeacherService {

    private final ModelMapper modelMapper;

    @Autowired private  UserService userService;
	
	@Autowired private TeacherRepository teacherRepository;

    TeacherService(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
	
	public void addNewTeacher(TeacherRequest teacherRequest) {
		User teacherUser=new User();
		modelMapper.map(teacherUser, User.class);
		teacherUser.setUsername(teacherRequest.getUsername());
		teacherUser.setPassword(teacherRequest.getPassword());
		teacherUser.setRole("TEACHER");
		User savedUser=userService.save(teacherUser);
		Teacher newTeacher=new Teacher();
		newTeacher.setFirstName(teacherRequest.getFirstName());
		newTeacher.setLastName(teacherRequest.getLastName());
		newTeacher.setSubject(teacherRequest.getSubject());
		newTeacher.setUser(savedUser);
		teacherRepository.save(newTeacher);
	}
	
	public Optional<Teacher> findParentsById(long id){
		return teacherRepository.findById(id);
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();

}}
