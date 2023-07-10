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
import org.springframework.web.client.RestTemplate;

import com.anthem.employee.model.Employee;
import com.anthem.employee.service.EmployeeService;
// Annotation
@RestController

// Class
public class EmpController {
	
	@Autowired
	private EmployeeService service;

	@PostMapping("/addEmployee")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee emp) throws Exception{
		return service.saveEmp(emp);  
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Employee>> getEmployees() throws Exception {
	
		return service.findAllEmp(); 
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> getEmployeebyId(@PathVariable String id) {
		
		try {
			return service.findEmp(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		} 
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) throws Exception {
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
	public ResponseEntity<List<Employee>> getEmployeesWithSameName(@PathVariable String name) throws Exception{
		return service.getEmployeesWithSameName(name);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteemployee(@PathVariable String id) throws Exception{
		return service.deleteEmp(id);
	}
	@GetMapping("/employees/manager/{managerid}")
	public ResponseEntity<List<Employee>> getEmployeesByManager(@PathVariable("managerid") String managerid) throws Exception{
		
		return service.getEmployeesByManagerId(managerid);
	}

}
