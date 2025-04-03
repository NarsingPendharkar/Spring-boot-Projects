package org.school.DTO;

public class StudentCourseDTO {
		
	    private String courseName;
	    private String description;
	    private String teacherName;
	    private Long teacherId;
	    private Long courseId;
	    private Long studentId;
	    
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
		public String getTeacherName() {
			return teacherName;
		}
		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}
		public Long getTeacherId() {
			return teacherId;
		}
		public void setTeacherId(Long teacherId) {
			this.teacherId = teacherId;
		}
		public Long getCourseId() {
			return courseId;
		}
		public void setCourseId(Long courseId) {
			this.courseId = courseId;
		}
		
		public StudentCourseDTO(String courseName, String description, String teacherName, Long teacherId,
				Long courseId, Long studentId) {
			super();
			this.courseName = courseName;
			this.description = description;
			this.teacherName = teacherName;
			this.teacherId = teacherId;
			this.courseId = courseId;
			this.studentId = studentId;
		}
		public StudentCourseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return String.format(
					"StudentCourseDTO [courseName=%s, description=%s, teacherName=%s, teacherId=%s, courseId=%s, studentId=%s]",
					courseName, description, teacherName, teacherId, courseId, studentId);
		}
		public Long getStudentId() {
			return studentId;
		}
		public void setStudentId(Long studentId) {
			this.studentId = studentId;
		}
	    
	    
	    
		
	    

}
