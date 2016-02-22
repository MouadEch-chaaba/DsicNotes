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
import com.dsic.persistance.beansPersistor.BeansPersistor;
import com.dsic.persistance.beansPersistor.BeansPersistorHelper;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting the identifier of the note
		Integer identifier = Integer.valueOf(request.getParameter("identifier"));
		
		// Getting the currentUser from the session
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		
		Note n = new Note();
		n.setIdentifier(identifier);
		
		try {
			BeansPersistorHelper.prepareForDeletion(n);
			BeansPersistor.getInstance().takeCareOf(n);
			
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
			response.sendRedirect(request.getContextPath()+"/views/dashboard.jsp");
		} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
