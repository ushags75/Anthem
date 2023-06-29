// Java Program to Illustrate EmpRepo File
package com.anthem.employee.controller.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.anthem.employee.model.Employee;

public interface EmpRepository
	extends MongoRepository<Employee, String>{
 
	@Query("{'location':?0,'designation':?1}")
	List<Employee> findByLocationAndDesignation(String location,String designation);

	List<Employee> findByName(String name);
}
