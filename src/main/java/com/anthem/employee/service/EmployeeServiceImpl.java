package com.anthem.employee.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anthem.employee.controller.repository.EmpRepository;
import com.anthem.employee.model.Employee;
import com.anthem.employee.utility.StringUtils;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmpRepository repo;

	@Override
	public String saveEmp(Employee emp) {
		if(emp==null)
			return "Please provide input";
		if(!StringUtils.validateString(emp.getId()))
			return "Employee ID cannot be null";
		if(!StringUtils.validateString(emp.getName()))
			return "Employee name cannot be null";
		if(!StringUtils.validateString(emp.getDesignation()))
			return "Employee designation cannot be null";
		if(!emp.getId().matches("^[a-zA-Z0-9]+$"))
			return "Employee ID cannot contain special character";

		if(isEmployeeIdDuplicate(emp.getId())) {
			return "Employee ID already exists";
		}
		Employee savedEmp=repo.save(emp);
		if(savedEmp!=null && savedEmp.getId()!=null)
			 return "Employee record Saved successfully";
		else
			 return "Some error while saving Employee record";
	}

	public boolean isEmployeeIdDuplicate(String id) {
		//List<Employee> duplicateEmployees = repo.findAll().stream().filter(e->e.getId().equals(id) || e.getName().equals(name)).collect(Collectors.toList());
		//return duplicateEmployees.size()>1;	
	    return repo.findById(id).isPresent();
	}
	
	public List<Employee> getEmployeesWithSameName(String name){
		return repo.findByName(name);
	}

	@Override
	public List<Employee> findAllEmp() {
	    List<Employee> employees=repo.findAll();
	    
	    employees.sort(Comparator.comparing(Employee::getId));
		return employees;

	}

	@Override
	public Employee findEmp(String empId) {
		// TODO Auto-generated method stub
		Optional<Employee> optional = repo.findById(empId);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException("Employee not found for id :" + empId);
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return repo.save(emp);
	}

	@Override
	public String deleteEmp(String empId) {
		// TODO Auto-generated method stub
		if (repo.findById(empId).isPresent()) {
			repo.deleteById(empId);
			return "Deleted Succesfully";
		} else
			return null;
	}

}
