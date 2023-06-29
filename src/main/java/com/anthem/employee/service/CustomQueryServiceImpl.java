package com.anthem.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anthem.employee.controller.repository.EmpRepository;
import com.anthem.employee.model.Employee;

@Component
public class CustomQueryServiceImpl implements CustomQueryService {

	@Autowired
	private EmpRepository repo;

	@Override
	public List<Employee> findByLocationAndDesignation(String location, String designation) {
		return repo.findByLocationAndDesignation(location,designation);
	}
	
}
