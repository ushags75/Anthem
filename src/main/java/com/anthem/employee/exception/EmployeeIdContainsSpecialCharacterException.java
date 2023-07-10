package com.anthem.employee.exception;

public class EmployeeIdContainsSpecialCharacterException extends RuntimeException {
	public EmployeeIdContainsSpecialCharacterException(String message) {
		super(message);
	}

}
