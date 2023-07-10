package com.anthem.employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.anthem.employee.model.Employee;
@Component
public interface EmployeeService {
	
	public ResponseEntity<String> saveEmp(Employee emp) throws Exception;
	public ResponseEntity<Employee> findEmp(String empId) throws Exception;
	public ResponseEntity<String> deleteEmp(String empId) throws Exception;
	public ResponseEntity<Employee> updateEmployee(Employee emp) throws Exception;
	public ResponseEntity<List<Employee>> getEmployeesWithSameName(String name) throws Exception;
	ResponseEntity<List<Employee>> findAllEmp(boolean ascending) throws Exception;
	ResponseEntity<List<Employee>> findAllEmp() throws Exception;
    ResponseEntity<List<Employee>> getEmployeesByManagerId(String managerid) throws Exception;
}

