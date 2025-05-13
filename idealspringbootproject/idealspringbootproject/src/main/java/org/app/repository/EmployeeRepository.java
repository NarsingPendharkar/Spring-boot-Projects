package org.app.repository;

import java.util.Optional;

import org.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee>findById(Long id);
}
