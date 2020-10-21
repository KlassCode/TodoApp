package com.klasscode.todoapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.klasscode.todoapp.model.Todo;
import com.klasscode.todoapp.utils.JDBCUtils;

public class TodoImplement implements TodoDAO {

	private static final String INSERT_TODOS = "INSERT INTO todos(title,username,description,targetDate,isDone) VALUES(?,?,?,?,?);";
	private static final String SELECT_TODO_BYID = "SELECT * FROM todos WHERE id = ?";
	private static final String SELECT_ALL_TODOS = "SELECT * FROM todos";
	private static final String UPDATE_TODO = "UPDATE todos SET title =? ,username = ? ,description=?, targetDate=?,isDone=? WHERE id= ? ";
	private static final String DELETE_TODO = "DELETE FROM todos WHERE id = ?";

	@Override
	public void insertTodo(Todo todo) {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = JDBCUtils.getConnection().prepareStatement(INSERT_TODOS)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getUsername());
			ps.setString(3, todo.getDescription());
			ps.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			ps.setBoolean(5, todo.getStatus());

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			JDBCUtils.printSQLException(e);
		}

	}

	@Override
	public Todo selectTodo(long todoId) {
		// TODO Auto-generated method stub
		Todo todo = null;

		try (PreparedStatement ps = JDBCUtils.getConnection().prepareStatement(SELECT_TODO_BYID)) {

			ps.setLong(1, todoId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("targetDate").toLocalDate();
				boolean isDone = rs.getBoolean("isDone");

				todo = new Todo(id, title, username, description, targetDate, isDone);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		// TODO Auto-generated method stub
		List<Todo> todos = null;

		try (PreparedStatement ps = JDBCUtils.getConnection().prepareStatement(SELECT_ALL_TODOS);
				ResultSet rs = ps.executeQuery()) {

			todos = new ArrayList<Todo>();
			while (rs.next()) {

				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("targetDate").toLocalDate();
				boolean isDone = rs.getBoolean("isDone");

				todos.add(new Todo(id, title, username, description, targetDate, isDone));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return todos;
	}

	@Override
	public boolean deleteTodo(int id){
		// TODO Auto-generated method stub
		boolean rowDeleted = false;
		try (PreparedStatement statement = JDBCUtils.getConnection().prepareStatement(DELETE_TODO);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return rowDeleted;
	}

	@Override

	public boolean updateTodo(Todo todo) {
		boolean rowUpdated = false;
		try (PreparedStatement statement = JDBCUtils.getConnection().prepareStatement(UPDATE_TODO);) {
			
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getUsername());
			statement.setString(3, todo.getDescription());
			statement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			statement.setBoolean(5, todo.getStatus());
			statement.setLong(6, todo.getId());
			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDBCUtils.printSQLException(e);
		}
		return rowUpdated;
	}

}
