package org.school.DTO;

public class CourseDTO {
	
	private Long id;
	private String courseName;
    private String description;
    private Long teacherId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public CourseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CourseDTO(Long id, String courseName, String description, Long teacherId, String teacherName) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.description = description;
		this.teacherId = teacherId;
		
	}
	@Override
	public String toString() {
		return String.format("CourseDTO [id=%s, courseName=%s, description=%s, teacherId=%s]", id, courseName,
				description, teacherId);
	}
	

}
