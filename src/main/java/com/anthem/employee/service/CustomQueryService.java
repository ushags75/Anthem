package com.anthem.employee.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import com.anthem.employee.model.Employee;

public interface CustomQueryService {
	
	List<Employee> findByLocationAndDesignation(String location, String designation);


}
