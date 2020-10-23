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
import com.klasscode.todoapp.utils.FunctionsUtils;

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
			break;

		case "/edit":
			showEditForm(request, response);
			break;

		case "/update":
			try {
				updateTodo(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "/delete":
			deleteTodo(request, response);
			break;

		default:
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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

	private void insertTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		String title = request.getParameter("title");

		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));

		LocalDate targetDate = null;
		try {
			targetDate = LocalDate.parse(request.getParameter("targetDate"));

			String[] fields = { title, description };

			if (FunctionsUtils.checkEmptyFields(fields)) {
				request.setAttribute("errorInsert", "Remplissez correctement les champs");
				request.getRequestDispatcher("/todo/todo-form.jsp").forward(request, response);

			} else {

				if (session.getAttribute("UserConnected") != null) {
					LoginBean user = (LoginBean) session.getAttribute("UserConnected");
					String userName = user.getUsername();
					Todo newTodo = new Todo(title, userName, description, targetDate, isDone);
					todoDao.insertTodo(newTodo);
					response.sendRedirect("list");
				} else {
					request.setAttribute("errorInsert", "la session a expire reconnectez vous ");
					request.getRequestDispatcher("/todo/todo-form.jsp").forward(request, response);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Date Erreur");
			request.setAttribute("errorInsert", "Remplissez correctement les champs");
			request.getRequestDispatcher("/todo/todo-form.jsp").forward(request, response);
		}

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));
		Todo todo = todoDao.selectTodo(id);
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("todo/todo-form.jsp").forward(request, response);
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		String title = request.getParameter("title");
		HttpSession session = request.getSession();
		LoginBean login = (LoginBean) session.getAttribute("UserConnected");
		String userName = login.getUsername();
		String description = request.getParameter("description");
		// DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		String [] fields = { title,description};
		
		if(FunctionsUtils.checkEmptyFields(fields)) {
			
			request.setAttribute("errorInsert", "Remplissez correctement les champs");
			Todo todo = todoDao.selectTodo(id);
			request.setAttribute("todo", todo);
			request.getRequestDispatcher("/todo/todo-form.jsp").forward(request, response);
		}else {
			Todo todoUpdate = new Todo(id, title, userName, description, targetDate, isDone);
			todoDao.updateTodo(todoUpdate);
			response.sendRedirect("list");
		}
		
		
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));

		try {
			todoDao.deleteTodo(id);
			response.sendRedirect("list");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Todo> todos = todoDao.selectAllTodos();
		request.setAttribute("todoList", todos);
		request.getRequestDispatcher("todo/todo-list.jsp").forward(request, response);
	
	}

}
