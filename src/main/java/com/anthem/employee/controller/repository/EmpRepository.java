// Java Program to Illustrate BookRepo File
package com.anthem.employee.controller.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anthem.employee.model.Employee;

public interface EmpRepository
	extends MongoRepository<Employee, String> {
}
