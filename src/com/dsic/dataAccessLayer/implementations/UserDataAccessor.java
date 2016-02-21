package com.dsic.dataAccessLayer.implementations;

import java.util.ArrayList;

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

	@Override
	public ArrayList<Object> getAll() {
		
		return null;
	}

}
