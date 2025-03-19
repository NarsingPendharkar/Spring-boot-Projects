package com.School360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School360.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
