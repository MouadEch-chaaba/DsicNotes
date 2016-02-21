package com.dsic.dataAccessLayer.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dsic.beans.User;
import com.dsic.connections.ConnectionManager;
import com.dsic.dataAccessLayer.interfaces.IDataAccessor;

public class UserDataAccessor implements IDataAccessor {
	
	private static UserDataAccessor instance = null;
	
	private UserDataAccessor(){
		
	}
	
	public static UserDataAccessor getInstance(){
		return instance != null ? instance : (instance = new UserDataAccessor());
	}

	@Override
	public Object get(int identifier) {
		
		return null;
	}

	@Override
	public Object get(String identifier) {
		
		return null;
	}
	
	public Object get(String login,String password) throws SQLException, ClassNotFoundException{
		// Getting a connection to database
		Connection connection = ConnectionManager.getConnection();
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery("SELECT Id,firstname,lastname FROM Utilisateurs WHERE login='"+login+"' AND pass='"+password+"'");
		
		User user = null;
		
		while(result.next()){
			user = new User();
			user.setIdentifier(result.getInt("Id"));
			user.setFirstName(result.getString("firstname"));
			user.setLastName(result.getString("lastname"));
			user.setLogin(login);
			user.setPassword(password);
			break;
		}
		return user;
	}

	@Override
	public ArrayList<Object> getAll() {
		
		return null;
	}

}
