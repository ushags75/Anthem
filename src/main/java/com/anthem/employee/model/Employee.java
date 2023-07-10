// Importing required classes
package com.anthem.employee.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
// Class
public class Employee
{

	// Attributes
	@Id
	private String id;
	private String name;
	private String designation;
	private String location;
	private String managerid;

}
