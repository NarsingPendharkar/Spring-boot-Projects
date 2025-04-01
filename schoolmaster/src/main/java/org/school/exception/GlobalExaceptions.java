package org.school.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExaceptions {



	@ExceptionHandler(TeacherAlreadyPresent.class)
	@ResponseBody
	public String teacherAlreadyPresentException(TeacherAlreadyPresent exception) {
		return "<html><body>"
                + "<h3 style='color:red;'>"+exception.getMessage()+"</h3>"
                + "<script>"
                + "setTimeout(function(){ window.location.href='/dashboards'; }, 3000);"
                + "</script>"
                + "</body></html>";
	}

}
