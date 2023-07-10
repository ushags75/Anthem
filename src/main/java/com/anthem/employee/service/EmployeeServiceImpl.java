package com.anthem.employee.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.anthem.employee.controller.repository.EmpRepository;
import com.anthem.employee.exception.EmployeeDesignationNotFoundException;
import com.anthem.employee.exception.EmployeeIdContainsSpecialCharacterException;
import com.anthem.employee.exception.EmployeeIdNotFoundException;
import com.anthem.employee.exception.EmployeeNameNotFoundException;
import com.anthem.employee.model.Employee;
import com.anthem.employee.utility.StringUtils;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmpRepository repo;

	@Override
	public ResponseEntity<String> saveEmp(Employee emp) throws Exception {
		try {
			if (emp == null)
				return ResponseEntity.badRequest().body("Please provide input");
			if (!StringUtils.validateString(emp.getId()))
				throw new EmployeeIdNotFoundException("Employee ID cannot be null");
			if (!StringUtils.validateString(emp.getName()))
				throw new EmployeeNameNotFoundException("Employee name cannot be null");
			if (!StringUtils.validateString(emp.getDesignation()))
				throw new EmployeeDesignationNotFoundException("Employee designation cannot be null");
			if (!emp.getId().matches("^[a-zA-Z0-9]+$"))
				throw new EmployeeIdContainsSpecialCharacterException("Employee ID cannot contain special character");

			if (isEmployeeIdDuplicate(emp.getId())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee ID already exists");
			}
			Employee savedEmp = repo.save(emp);
			if (savedEmp != null && savedEmp.getId() != null)
				return ResponseEntity.ok("Employee record Saved successfully");
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Some error occured while saving Employee record");
		} catch (EmployeeIdNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EmployeeNameNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EmployeeDesignationNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EmployeeIdContainsSpecialCharacterException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			throw new Exception("An error Occured", e);
		}
	}

	public boolean isEmployeeIdDuplicate(String id) {
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
			List<Employee> employees = repo.findAll();
			if (ascending) {
				employees.sort(Comparator.comparing(Employee::getId));
			} else {
				employees.sort(Comparator.comparing(Employee::getId).reversed());
			}
			responseEntity = new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (ArrayIndexOutOfBoundsException e) { // use customized exception
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
		} catch (ArrayIndexOutOfBoundsException e) { // use customized exception
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new Exception();
		}
		return employee;
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(Employee emp) throws Exception {
		try {
			Employee updatedEmployee = repo.save(emp);
			return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public ResponseEntity<String> deleteEmp(String empId) throws Exception {

		try {
			Optional<Employee> optional = repo.findById(empId);
			if (optional.isPresent()) {
				repo.deleteById(empId);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public ResponseEntity<List<Employee>> findAllEmp() throws Exception {
		try {
			List<Employee> employees = repo.findAll();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesWithSameName(String name) throws Exception{
        try {
        	List<Employee> employees = repo.findByName(name);
        	return new ResponseEntity<>(employees,HttpStatus.OK);
        }catch(RuntimeException e) {
        	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }catch(Exception e) {
        	throw new Exception();
        }
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesByManagerId(String managerid) throws Exception {
		try {
			List<Employee> employees = repo.findByManagerId(managerid);
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
