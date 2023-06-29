package com.anthem.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.anthem.employee.model.Employee;
@Component
public interface EmployeeService {
	
	public String saveEmp(Employee emp);
	public ResponseEntity<Employee> findEmp(String empId) throws Exception;
	public String deleteEmp(String empId);
	public Employee updateEmployee(Employee emp);
	public List<Employee> getEmployeesWithSameName(String name);
	ResponseEntity<List<Employee>> findAllEmp(boolean ascending) throws Exception;
	List<Employee> findAllEmp();
}