package com.anthem.employee.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Employee> getEmployeebyId(@PathVariable String id) {
		
		try {
			return service.findEmp(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		} 
		
	}
	
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return service.updateEmployee(emp);
	}
	@GetMapping("/ascending")
	public ResponseEntity<List<Employee>> findAllEmpAsc() throws Exception{
		return service.findAllEmp(true);
	}
	@GetMapping("/descending")
	public ResponseEntity<List<Employee>> findAllEmpDsc() throws Exception{
		return service.findAllEmp(false);
	}
	@GetMapping("/getByname/{name}")
	public List<Employee> getEmployeesWithSameName(@PathVariable String name){
		return service.getEmployeesWithSameName(name);
	}
	@DeleteMapping("/delete/{id}")
	public String deleteemployee(@PathVariable String id){
		return service.deleteEmp(id);
	}

}
