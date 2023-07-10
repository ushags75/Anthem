package com.anthem.employee.exception;

public class EmployeeIdNotFoundException extends RuntimeException {
	public EmployeeIdNotFoundException(String message) {
		super(message);
	}

}
