package org.school.service;

import java.util.List;

import org.school.entity.Attendance;
import org.school.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public void markAttendance(Attendance attendance) {
		attendanceRepository.save(attendance);
	}
	
	public List<Attendance> studentAttendance(long id){
		return attendanceRepository.findByStudentId(id);
	}
	

}
