package org.app.service;

import java.util.List;

import org.app.entity.Employee;

public interface EmployeeService {
	public Employee saveEmployee(Employee newEmployee);
	public void deleteEmployee(Long id);
	public List<Employee>getAllEmployees();
	public Employee updateEmployee(Employee updatedEmployee,Long id);
	public Employee getEmployeeById(Long id);
}
