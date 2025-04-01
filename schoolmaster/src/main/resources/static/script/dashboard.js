
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
	
	
});
