package org.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.app.entity.Employee;
import org.app.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.Delegate;
import lombok.Getter;

@RestController
@RequestMapping("/api/emp")
@Validated
public class EmployeeController {
	
	@Autowired EmployeeServiceImpl empServiceImpl;
	
	@GetMapping("/{empid}")
	public Employee getEmployeeById(@PathVariable("empid") Long id) {
		return empServiceImpl.getEmployeeById(id);
	}
	@PostMapping("/create")
	public Employee newEmployeeAdd( @RequestBody  @Valid Employee newempEmployee) {
		return empServiceImpl.saveEmployee(newempEmployee);
	}
	@GetMapping()
	public List<Employee> getAllEmployee(){
		return empServiceImpl.getAllEmployees();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Object>> deleteEmpById(@PathVariable Long id){
		empServiceImpl.deleteEmployee(id);
		Map<String, Object> response=new HashMap<>();
		 response.put("message", "Product deleted successfully");
	     return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id,@Valid @RequestBody Employee updated){
		return ResponseEntity.ok(empServiceImpl.updateEmployee(updated, id));
	}

}
