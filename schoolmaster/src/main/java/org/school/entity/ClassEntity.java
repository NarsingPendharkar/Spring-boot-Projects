package org.school.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    

    @OneToMany(mappedBy = "assignedClass", cascade = CascadeType.ALL)
    private List<Student> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public ClassEntity(Long id, String className, Teacher teacher, List<Student> students) {
		super();
		this.id = id;
		this.className = className;
		this.teacher = teacher;
		this.students = students;
	}
	

	public ClassEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
