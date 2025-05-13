package org.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator= "empid")
	@SequenceGenerator(allocationSize = 1,initialValue = 101,name = "empid",sequenceName = "empid")
	private Long id;
	@NotBlank(message = "Invalid employee name !")
	private String name;
	@NotBlank(message = "Invalid employee id !")
	private String empid;
	@Positive(message = "Invalid age")
	private Integer age;
	@Positive(message = "Salary could not be negative")
	private Double Salary;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return Salary;
	}
	public void setSalary(Double salary) {
		Salary = salary;
	}
	@Override
	public String toString() {
		return String.format("Employee [id=%s, name=%s, empid=%s, age=%s, Salary=%s]", id, name, empid, age, Salary);
	}
	public Employee(Long id, @NotBlank(message = "Invalid name") String name,
			@NotBlank(message = "Invalid empid") String empid, @NotBlank(message = "Invalid age") Integer age,
			@Positive(message = "Salary could not be negative") Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.empid = empid;
		this.age = age;
		Salary = salary;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
