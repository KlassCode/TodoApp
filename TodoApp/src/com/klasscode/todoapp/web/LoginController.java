package com.klasscode.todoapp.web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klasscode.todoapp.dao.LoginDAO;
import com.klasscode.todoapp.model.LoginBean;
import com.klasscode.todoapp.utils.FunctionsUtils;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao;
	final static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		loginDao = new LoginDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("erreurConnexion", null);
		request.setAttribute("error", null);
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String[] fields = { username, password };

		if (FunctionsUtils.checkEmptyFields(fields)) {
			request.setAttribute("erreurConnexion", "Remplissez les champs correctement");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			LoginBean login = new LoginBean();
			login.setUsername(username);
			login.setPassword(password);

			if (loginDao.validate(login)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("UserConnected", login);
				request.getRequestDispatcher("todo/todo-list.jsp").forward(request, response);
				logger.info(username + " est connecte");

			} else {

				request.setAttribute("error", "Identifiant/password incorrect");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				logger.log(Level.WARNING,
						"Tentative de Connexion echoue name : " + username + " / password " + password);
			}
		}

	}

}
