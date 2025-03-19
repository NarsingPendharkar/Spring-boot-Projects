package org.school.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Teacher extends User {
    private String employeeId;
    private String subjectSpecialization;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassEntity> assignedClasses; // mappedBy="teacher" now correctly refers to the field in ClassEntity
}
