package com.anthem.employee.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		try {
			if (emp == null)
				return "Please provide input";
			if (!StringUtils.validateString(emp.getId()))
				return "Employee ID cannot be null";
			if (!StringUtils.validateString(emp.getName()))
				return "Employee name cannot be null";
			if (!StringUtils.validateString(emp.getDesignation()))
				return "Employee designation cannot be null";
			if (!emp.getId().matches("^[a-zA-Z0-9]+$"))
				return "Employee ID cannot contain special character";

			if (isEmployeeIdDuplicate(emp.getId())) {
				return "Employee ID already exists";
			}
			Employee savedEmp = repo.save(emp);
			if (savedEmp != null && savedEmp.getId() != null)
				return "Employee record Saved successfully";
			else
				return "Some error while saving Employee record";
		} catch (Exception e) {
			return "An error occurred";
		}
	}

	public boolean isEmployeeIdDuplicate(String id) {
		// List<Employee> duplicateEmployees =
		// repo.findAll().stream().filter(e->e.getId().equals(id) ||
		// e.getName().equals(name)).collect(Collectors.toList());
		// return duplicateEmployees.size()>1;
		try {
			return repo.findById(id).isPresent();
		} catch (Exception e) {
			return false;
		}
	}

	public List<Employee> findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public ResponseEntity<List<Employee>> findAllEmp(boolean ascending) throws Exception {
		ResponseEntity<List<Employee>> responseEntity;
		try {
			List<Employee> employees=repo.findAll();
		if (ascending) {
			employees.sort(Comparator.comparing(Employee::getId));
		} else {
			employees.sort(Comparator.comparing(Employee::getId).reversed());
		}
		responseEntity=new ResponseEntity<>(employees,HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception();
		}
		return responseEntity;

	}

	@Override
	public ResponseEntity<Employee> findEmp(String empId) throws Exception {
		//
		ResponseEntity<Employee> employee = null;
		try {
			Optional<Employee> optional = repo.findById(empId);

			if (optional.isPresent()) {
				employee = new ResponseEntity<>(optional.get(), HttpStatus.OK);
			} else {

				throw new Exception();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new Exception();
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

	@Override
	public List<Employee> findAllEmp() {
		return repo.findAll();
	}

	@Override
	public List<Employee> getEmployeesWithSameName(String name) {

		return repo.findByName(name);
	}

}
