package com.anthem.employee.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anthem.employee.model.Employee;
import com.anthem.employee.service.EmployeeService;

// Annotation
@RestController

// Class
public class EmpController {
	
	@Autowired
	private EmployeeService service;

	@PostMapping("/addEmployee")
	public String saveEmployee(@RequestBody Employee emp){
		return service.saveEmp(emp);  
	}

	@GetMapping("/findAll")
	public List<Employee> getEmployees() {
	
		return service.findAllEmp(); 
	}

	@GetMapping("/getById/{id}")
	public Employee getEmployeebyId(@PathVariable String id) {
		
		return service.findEmp(id); 
	}
	
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return service.updateEmployee(emp);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteemployee(@PathVariable String id){
		service.deleteEmp(id);
	
		return "Deleted Successfully";
	}

}
