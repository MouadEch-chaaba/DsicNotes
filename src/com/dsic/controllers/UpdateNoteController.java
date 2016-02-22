package com.dsic.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsic.beans.Note;
import com.dsic.beans.User;
import com.dsic.dataAccessLayer.implementations.NoteDataAccessor;
import com.dsic.persistance.beansPersistor.BeansPersistor;

@WebServlet("/UpdateNoteController")
public class UpdateNoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateNoteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting the currentNote from the session
		Note currentNote = (Note) request.getSession().getAttribute("currentNote");
		
		// Getting the currentUser from the session
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		
		// Getting the BeansPersistor
		try {
			BeansPersistor.getInstance().takeCareOf(currentNote);
			
			// Getting all notes owned by the current user
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

}
