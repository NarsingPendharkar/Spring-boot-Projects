package org.school.entity;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties({"usename", "password"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Separate User entity

    private String admissionNumber;
    private Date admissionDate;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity assignedClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendanceRecords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public ClassEntity getAssignedClass() {
		return assignedClass;
	}

	public void setAssignedClass(ClassEntity assignedClass) {
		this.assignedClass = assignedClass;
	}

	public List<Attendance> getAttendanceRecords() {
		return attendanceRecords;
	}

	public void setAttendanceRecords(List<Attendance> attendanceRecords) {
		this.attendanceRecords = attendanceRecords;
	}

	public Student(Long id, User user, String admissionNumber, Date admissionDate, ClassEntity assignedClass,
			List<Attendance> attendanceRecords) {
		super();
		this.id = id;
		this.user = user;
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.assignedClass = assignedClass;
		this.attendanceRecords = attendanceRecords;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
