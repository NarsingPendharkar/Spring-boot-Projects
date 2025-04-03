package org.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TeacherAlreadyPresent extends Exception {
	public TeacherAlreadyPresent(String message) {
        super(message);
    }

}
