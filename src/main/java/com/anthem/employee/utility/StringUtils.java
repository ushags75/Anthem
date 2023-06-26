package com.anthem.employee.utility;

public class StringUtils {
	
	public static boolean validateString(String id) {
		// validate input string
		if(id==null || id.isEmpty())
			return false;
		else
			return true;
	}


}
