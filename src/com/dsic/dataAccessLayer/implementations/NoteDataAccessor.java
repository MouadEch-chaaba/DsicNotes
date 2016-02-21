package com.dsic.dataAccessLayer.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dsic.beans.Note;
import com.dsic.beans.User;
import com.dsic.connections.ConnectionManager;
import com.dsic.dataAccessLayer.interfaces.IDataAccessor;

public class NoteDataAccessor implements IDataAccessor {
	
	private static NoteDataAccessor instance = null;
	

	private NoteDataAccessor(){
		
	}
	
	public static NoteDataAccessor getInstance(){
		return instance != null ? instance : (instance = new NoteDataAccessor());
	}

	@Override
	public Object get(int identifier) {
		
		return null;
	}

	@Override
	public Object get(String identifier) {
		
		return null;
	}

	@Override
	public ArrayList<Object> getAll() {
		
		return null;
	}
	
	public ArrayList<Note> getAllForOwner(User owner) throws ClassNotFoundException, SQLException{
		// Getting a connection to database
		Connection connection = ConnectionManager.getConnection();
		Statement statement = connection.createStatement();
		
		ArrayList<Note> notes = new ArrayList<Note>();
		
		ResultSet result = statement.executeQuery("SELECT * FROM notes WHERE id_user = "+owner.getIdentifier());
		
		Note note;
		
		while(result.next()){
			note = new Note();
			note.setContent(result.getString("content"));
			note.setDate(result.getString("createdAt"));
			note.setIdentifier(result.getInt("Id"));
			note.setImportance(result.getString("importance"));
			note.setOwner(owner.getIdentifier());
			notes.add(note);
		}
		
		return notes;
	}

}
