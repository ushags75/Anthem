package com.anthem.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anthem.employee.model.Employee;
import com.anthem.employee.service.CustomQueryService;

@RestController
public class CustomQueryController {
	private final CustomQueryService custom;
	
	@Autowired
	public CustomQueryController(CustomQueryService custom) {
		this.custom=custom;
	}

	@GetMapping("/location/{location}/designation/{designation}")
	public List<Employee> getEmployeesByLocationAndDesignation(@PathVariable String location, @PathVariable String designation){
		return custom.findByLocationAndDesignation(location,designation);
	}

}
