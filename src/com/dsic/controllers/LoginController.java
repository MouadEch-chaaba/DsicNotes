package com.dsic.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsic.beans.User;
import com.dsic.dataAccessLayer.implementations.UserDataAccessor;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirecting to Login page
		request.getServletContext().getRequestDispatcher("/views/index.html").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting the current User From the session
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		
		// Getting UserBean DataAccessor
		UserDataAccessor userDataAccessor = UserDataAccessor.getInstance();
		
		// Checking if the user exists in the database
		if(userDataAccessor.get(currentUser.getLogin(),currentUser.getPassword()) != null){
			// Redirecting to dashBoard
			request.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
		}else{
			// User not exists => redirect to user not found page
			request.getServletContext().getRequestDispatcher("/views/Error.jsp").forward(request,response);
		}
	}

}
