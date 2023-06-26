package com.anthem.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.anthem.employee.model.Employee;
@Component
public interface EmployeeService {
	
	public String saveEmp(Employee emp);
	public List<Employee> findAllEmp();
	public Employee findEmp(String empId);
	public String deleteEmp(String empId);
	public Employee updateEmployee(Employee emp);

}