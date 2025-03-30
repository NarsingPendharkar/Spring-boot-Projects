package org.school.DTO;

public class ClassMetadata {

	    private Long id;
	    private String className;
	    private Long teacherId;
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
		public ClassMetadata(Long id, String className, Long teacherId) {
			super();
			this.id = id;
			this.className = className;
			this.teacherId = teacherId;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ClassMetadata [id=");
			builder.append(id);
			builder.append(", className=");
			builder.append(className);
			builder.append(", teacherId=");
			builder.append(teacherId);
			builder.append("]");
			return builder.toString();
		} 
	    

}
