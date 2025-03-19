package org.school.model;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Student extends User {
	
    private String admissionNumber;
    private Date admissionDate;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity assignedClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendanceRecords;
}
