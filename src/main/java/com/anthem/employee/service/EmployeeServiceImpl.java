package com.anthem.employee.service;

import java.util.List;
import java.util.Optional;

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
		Employee savedEmp=null;
		//Verify input is null or not
		if(emp==null)
			return "Please provide input";
		else
		{
			if(!StringUtils.validateString(emp.getId()))
				return "Employee ID cannot be null";
			else if(!emp.getId().matches("[^a-zA-Z0-9]+$"))
				return "Employee ID cannot contain special character";
			
			if(!validateString(emp.getName()))
				return "Employee name cannot be null";
		}
		if(emp==null || emp.getId()==null || emp.getId().isEmpty()) {
			return "Employee ID cannot be null";
		}
		if(emp.getName()==null || emp.getName().isEmpty()) {
			
		}
		if(emp.getDesignation()==null || emp.getDesignation().isEmpty()) {
			return "Employee designation cannot be null";
		}
		if() {
			
		}
		if(isEmployeeIdDuplicate(emp.getId())) {
			return "Employee ID already exists";
		}
		if(emp!=null)
		{
			savedEmp=repo.save(emp);
		}
		 if(savedEmp!=null && savedEmp.getId()!=null)
			 return "Employee record Saved successfully";
		 else
			 return "Some error while saving Employee record";
	}

	public boolean isEmployeeIdDuplicate(String id) {
		return repo.findById(id).isPresent();
	}

	@Override
	public List<Employee> findAllEmp() {
		// TODO Auto-generated method stub
		return repo.findAll();

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
