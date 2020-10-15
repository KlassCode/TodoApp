package com.klasscode.todoapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klasscode.todoapp.dao.TodoDAO;
import com.klasscode.todoapp.dao.TodoImplement;
import com.klasscode.todoapp.model.LoginBean;
import com.klasscode.todoapp.model.Todo;

/**
 * Servlet implementation class TodoController
 */
@WebServlet("/")
public class TodoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TodoDAO todoDao;

	public void init() {
		todoDao = new TodoImplement();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		System.out.println(action);

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;

		case "/insert":
			try {
				insertTodo(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/list":
			listTodo(request, response);
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("todo/todo-form.jsp").forward(request, response);

	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String userName = "KlassCode";
		LoginBean user = (LoginBean) session.getAttribute("UserConnected");
		if (user != null) {

//			userName = user.getUsername();
		}

		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		Todo newTodo = new Todo(title, userName, description, targetDate, isDone);
		todoDao.insertTodo(newTodo);
		response.sendRedirect("list");

	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Todo> todos = todoDao.selectAllTodos();
		request.setAttribute("todoList", todos);
		request.getRequestDispatcher("todo/todo-list.jsp").forward(request, response);
	}

}
