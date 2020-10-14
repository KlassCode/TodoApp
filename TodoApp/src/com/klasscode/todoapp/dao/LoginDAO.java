package com.klasscode.todoapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.klasscode.todoapp.model.LoginBean;
import com.klasscode.todoapp.utils.JDBCUtils;

public class LoginDAO {
	
	public boolean validate(LoginBean log) {
		
		boolean status = false;
		
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		
		try(PreparedStatement pr = JDBCUtils.getConnection().prepareStatement(sql)){
			
			pr.setString(1, log.getUsername());
			pr.setString(2, log.getPassword());
			
			try(ResultSet rs = pr.executeQuery()){
				status = rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		
		return status;
	}
}
