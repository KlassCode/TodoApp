package com.klasscode.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.klasscode.todoapp.model.User;
import com.klasscode.todoapp.utils.JDBCUtils;

public class UserDAO {
	
	public int register(User user) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "INSERT INTO users(firstname,lastname,username,password) VALUES(?,?,?,?);";
		try(Connection conn = JDBCUtils.getConnection();
			PreparedStatement pr = conn.prepareStatement(query)){
			
			pr.setString(1, user.getFirstName());
			pr.setString(2, user.getLastName());
			pr.setString(3, user.getUsername());
			pr.setString(4, user.getPassword());
			
			result = pr.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
}
