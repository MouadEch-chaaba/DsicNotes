package com.dsic.dataAccessLayer.implementations;

import java.util.ArrayList;

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

}
