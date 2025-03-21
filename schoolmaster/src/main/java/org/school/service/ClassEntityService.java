package org.school.service;

import org.school.entity.ClassEntity;
import org.school.repository.ClassEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassEntityService {

	@Autowired
	private ClassEntityRepository classRepository;

	public void saveClass(ClassEntity classEntity) {
		classRepository.save(classEntity);
	}

	public ClassEntity findbyclassname(String classname) {
		return classRepository.findByClassName(classname).orElse(null);
	}

}
