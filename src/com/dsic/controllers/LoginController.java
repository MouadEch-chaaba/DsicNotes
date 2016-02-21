package com.dsic.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsic.beans.Note;
import com.dsic.beans.User;
import com.dsic.dataAccessLayer.implementations.NoteDataAccessor;
import com.dsic.dataAccessLayer.implementations.UserDataAccessor;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirecting to Login page
		response.sendRedirect(request.getContextPath()+"/views/index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting the current User From the session
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		
		// Getting UserBean DataAccessor
		UserDataAccessor userDataAccessor = UserDataAccessor.getInstance();
		
		// Checking if the user exists in the database
		try {
			currentUser = (User) userDataAccessor.get(currentUser.getLogin(),currentUser.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(currentUser != null){
			// Restoring the currentUser in the session
			request.getSession().setAttribute("currentUser", currentUser);
			
			// Getting all notes of the current user
			NoteDataAccessor noteDataAccessor = NoteDataAccessor.getInstance();
			ArrayList<Note> notes = null;
			try {
				notes =  noteDataAccessor.getAllForOwner(currentUser);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			// Storing the userNotes in the session
			request.getSession().setAttribute("currentUserNotes", notes);
			
			// Redirecting to dashBoard
			request.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
		}else{
			// User not exists => redirect to user not found page
			request.getServletContext().getRequestDispatcher("/views/Error.jsp").forward(request,response);
		}
	}

}
