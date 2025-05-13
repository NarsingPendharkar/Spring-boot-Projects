package org.app.serviceImpl;

import java.util.List;

import org.app.entity.Employee;
import org.app.exception.EmployeeNotFound;
import org.app.repository.EmployeeRepository;
import org.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee newEmployee) {
		newEmployee.setEmpid("emp_" + newEmployee.getEmpid());
		return employeeRepository.save(newEmployee);
	}

	@Override
	public void deleteEmployee(Long id) {
		Employee exist = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFound("Unable to delete, Employee not found with id :" + id));
		if (exist != null) {
			employeeRepository.delete(exist);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee( @Valid Employee  updatedEmployee, Long id) {
		Employee presentEmp = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFound("Cannot update. Employee not found with ID: " + id));
		presentEmp.setAge(updatedEmployee.getAge());
		presentEmp.setEmpid("emp_" + updatedEmployee.getEmpid());
		presentEmp.setName(updatedEmployee.getName());
		presentEmp.setSalary(updatedEmployee.getSalary());
		return employeeRepository.save(presentEmp);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFound("Can not find. Employe not found with id:" + id));
	}

}
