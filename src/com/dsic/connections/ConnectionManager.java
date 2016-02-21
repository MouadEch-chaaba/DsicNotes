package com.dsic.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static Connection instance = null;
	
	private ConnectionManager(){
		
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		return instance != null ? instance : (instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/DsicNotes","root","toor"));
	}
	
}
