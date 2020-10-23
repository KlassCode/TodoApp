package com.klasscode.todoapp.utils;

public class FunctionsUtils {

	public static boolean checkEmptyFields(String[] fields) {

		boolean test = true;

		if (fields.length == 0) {
			test = true;
			System.out.println("aucun champ envoye");
		} else {
			System.out.println("le nombre de champ envoye est de  : "+ fields.length);
			for (String field : fields) {
				if (field.equals("")) {
					System.out.println("nom de champ : "+field);
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
