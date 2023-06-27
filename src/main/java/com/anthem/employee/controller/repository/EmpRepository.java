// Java Program to Illustrate EmpRepo File
package com.anthem.employee.controller.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anthem.employee.model.Employee;

public interface EmpRepository
	extends MongoRepository<Employee, String>{

	List<Employee> findByName(String name);
	
}
