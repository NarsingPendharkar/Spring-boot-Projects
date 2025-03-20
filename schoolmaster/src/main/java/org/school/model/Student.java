package org.school.model;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Student extends User {
	
    private String admissionNumber;
    private Date admissionDate;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity assignedClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendanceRecords;

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

	public Student(String admissionNumber, Date admissionDate, ClassEntity assignedClass,
			List<Attendance> attendanceRecords) {
		super();
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.assignedClass = assignedClass;
		this.attendanceRecords = attendanceRecords;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String firstName, String lastName,
			@Size(min = 10, max = 100, message = "Username should have a length between 10 and 100 characters.") @NotNull String username,
			String password, String role) {
		super(id, firstName, lastName, username, password, role);
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
    
}
