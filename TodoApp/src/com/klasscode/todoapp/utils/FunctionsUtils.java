package com.klasscode.todoapp.utils;

public class FunctionsUtils {

	public static boolean checkEmptyFields(String[] fields) {

		boolean test = true;

		if (fields.length == 0) {
			test = true;
		} else {
			
			for (String field : fields) {
				if (field.equals("")) {
					test = true;
					break;
				} else {
					test = false;
				}
			}
		}
		return test;
	}
	
}
