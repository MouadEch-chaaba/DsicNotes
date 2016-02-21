package com.dsic.persistance.interfaces;

public interface IQueryGenerator {
	
	public void prepareQueryFor(IPersistentBean bean) throws IllegalArgumentException, IllegalAccessException;
	
	public String getQuery();
}
