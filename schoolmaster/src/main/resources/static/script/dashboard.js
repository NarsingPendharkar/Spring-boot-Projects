/*
$(document).ready(function() {
	$(".section-link").click(function(e) {
		e.preventDefault();
		const target = $(this).attr("href");
		$(".main-content > div").hide();
		$(target).show();
	});

	// Show the dashboard section by default
	$("#dashboard").show();

	// Add course Form Toggle
	$("#addCourseBtn").click(function() {
		$("#manageCourses").hide();
		$("#addCourseForm").show();
	});
	$("#cancelAddCourse").click(function() {
		$("#addCourseForm").hide();
		$("#manageCourses").show();
	});
	// add greade form toggle
	$("#addGradeBtn").click(function() {
			$("#manageGrades").hide();
			$("#addGradeForm").show();
		});
		$("#cancelAddGreade").click(function() {
			$("#addGradeForm").hide();
			$("#manageGrades").show();
		});


	// Add user Form Toggle
	$("#addUserBtn").click(function() {
		$("#manageUsers").hide();
		$("#addUserForm").show();
	});
	$("#cancelAddUser").click(function() {
		$("#addUserForm").hide();
		$("#manageUsers").show();
	});
	
	// add toggel for fee section
	// Add user Form Toggle
		$("#addFeeBtn").click(function() {
			$("#manageFees").hide();
			$("#addFeeForm").show();
		});
		$("#cancelAddFee").click(function() {
			$("#addFeeForm").hide();
			$("#manageFees").show();
		});
		
		// Add report Form Toggle
				$("#generateReportBtn").click(function() {
					$("#generateReportForm").show();
					$("#manageReports").hide();
				});
				$("#cancelAddFee").click(function() {
					$("#generateReportForm").hide();
					$("#manageReports").show();
				});
				
		// attendence form
		$("#addAttendanceBtn").click(function() {
					$("#markAttendance").hide();
					$("#addAttendanceForm").show();
				});
				$("#cancelAddAttendance").click(function() {
					$("#addAttendanceForm").hide();
					$("#markAttendance").show();
				});
		// student section 
		
		// attendance
		// feestatus
		//grades
		//managecourse

	
});
*/

$(document).ready(function() {
	// Handle section switching
	$(".section-link").click(function(e) {
		e.preventDefault();
		const target = $(this).attr("href");
		$(".main-content > div").hide();
		$(target).show();
	});

	// Show the dashboard section by default
	$("#dashboard").show();

	// Function to handle show/hide toggle
	function toggleForm(buttonId, sectionToHide, sectionToShow, cancelButtonId) {
		$(buttonId).click(function() {
			$(sectionToHide).hide();
			$(sectionToShow).show();
		});
		$(cancelButtonId).click(function() {
			$(sectionToShow).hide();
			$(sectionToHide).show();
		});
	}

	// Toggle for Course Form
	toggleForm("#addCourseBtn", "#manageCourses", "#addCourseForm", "#cancelAddCourse");

	// Toggle for Grade Form
	toggleForm("#addGradeBtn", "#manageGrades", "#addGradeForm", "#cancelAddGrade");

	// Toggle for User Form
	toggleForm("#addUserBtn", "#manageUsers", "#addUserForm", "#cancelAddUser");

	// Toggle for Fee Form
	toggleForm("#addFeeBtn", "#manageFees", "#addFeeForm", "#cancelAddFee");

	// Toggle for Reports Form
	toggleForm("#generateReportBtn", "#manageReports", "#generateReportForm", "#cancelGenerateReport");

	// Toggle for Attendance Form
	toggleForm("#addAttendanceBtn", "#markAttendance", "#addAttendanceForm", "#cancelAddAttendance");


	// Fee Status
	toggleForm("#viewStudentFeeBtn", "#studentFees", "#viewFeeStatusForm", "#cancelViewFeeStatus");

	// Grades
	toggleForm("#viewStudentGradesBtn", "#studentGrades", "#viewGradesForm", "#cancelViewGrades");

	// Courses
	toggleForm("#viewStudentCoursesBtn", "#studentCourses", "#viewCoursesForm", "#cancelViewCourses");
	
	// Toggle for student Form
		toggleForm("#addStudentBtn", "#manageStudents", "#addStudentForm", "#cancelAddStudent");
		
		// Toggle for paretns Form
			toggleForm("#addParentBtn", "#manageParents", "#addParentForm", "#cancelAddParent");
			
			// Toggle for teacher Form
						toggleForm("#addTeacherBtn", "#manageTeachers", "#addTeacherForm", "#cancelAddTeacher");
			
			
});
