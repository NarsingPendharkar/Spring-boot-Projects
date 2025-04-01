
$(document).ready(function() {
	$(".section-link").click(function(e) {
		e.preventDefault();
		const target = $(this).attr("href");
		$(".main-content > div").hide();
		$(target).show();
	});

	// Show the dashboard section by default
	$("#dashboard").show();

	// Add Student Form Toggle
	$("#addCourseBtn").click(function() {
		$("#manageCourses").hide();
		$("#addCourseForm").show();
	});
	$("#cancelAddCourse").click(function() {
		$("#addCourseForm").hide();
		$("#manageCourses").show();
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
});
