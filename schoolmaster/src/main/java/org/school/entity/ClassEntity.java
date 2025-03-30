package org.school.entity;

import jakarta.persistence.*;

@Entity
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private Long teacherId; // Stores Teacher ID instead of direct reference
    
    
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
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public ClassEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassEntity(Long id, String className, Long teacherId) {
		super();
		this.id = id;
		this.className = className;
		this.teacherId = teacherId;
	}

    // Getters and Setters
    
}
