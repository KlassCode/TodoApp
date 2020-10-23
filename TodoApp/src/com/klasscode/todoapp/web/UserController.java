package com.klasscode.todoapp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.klasscode.todoapp.dao.UserDAO;
import com.klasscode.todoapp.model.User;
import com.klasscode.todoapp.utils.FunctionsUtils;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/register")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAO userdao;
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userdao = new UserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		request.setAttribute("ErrorMessage", null);
		request.setAttribute("NOTIFICATION", null);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		List<String> errors = new ArrayList<>();
		
		String[] fields = { firstName, lastName, userName, password };
		if (FunctionsUtils.checkEmptyFields(fields)) {
			errors.add("Remplissez tout les champs");
		}else {
			if(userName.length()<3) {
				errors.add("Nom d'utilisateur trop court(minimum 3 caracteres)");
			}
			if(password.length()<5) {
				errors.add("Mot de passe trop court minimum 5 caracteres");
			}
		}
		if(errors.size()==0) {
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUsername(userName);
			user.setPassword(password);
			
			if(userdao.register(user)==1) {
				request.setAttribute("NOTIFICATION", "User Registered with Success");
			}
			
		}
		request.setAttribute("ErrorMessage", errors);
		
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

}
