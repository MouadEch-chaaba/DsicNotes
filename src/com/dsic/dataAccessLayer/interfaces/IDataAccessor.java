package com.dsic.dataAccessLayer.interfaces;

import java.util.ArrayList;

public interface IDataAccessor {
	
	// Methods to get a record by identifier
	public Object get(int identifier);
	public Object get(String identifier);
	
	// Methods to get all records
	public ArrayList<Object> getAll();
	
}
