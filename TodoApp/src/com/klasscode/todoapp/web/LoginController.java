package com.klasscode.todoapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klasscode.todoapp.dao.LoginDAO;
import com.klasscode.todoapp.model.LoginBean;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		loginDao = new LoginDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean login = new LoginBean();
		
		login.setUsername(username);
		login.setPassword(password);
		
		if(loginDao.validate(login)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("UserConnected", login);
			request.getRequestDispatcher("/todo-list.jsp").forward(request, response);
		}else {
			response.sendRedirect("/login");
		}
		
	}

}
