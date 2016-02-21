package com.dsic.beans;

import com.dsic.annotations.TableColumnName;
import com.dsic.annotations.TableName;
import com.dsic.persistance.interfaces.IPersistentBean;

@TableName("Utilisateurs")
public class User implements IPersistentBean{
	
	@TableColumnName("Id")
	private int identifier;
	
	@TableColumnName("login")
	private String login;
	
	@TableColumnName("pass")
	private String password;
	
	@TableColumnName("firstname")
	private String firstName;
	
	@TableColumnName("lastname")
	private String lastName;
	
	
	public User(){
		
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
