package com.dsic.persistance.interfaces;

public interface IQueryGenerator {
	
	public void prepareQueryFor(IPersistentBean bean);
	
	public String getQuery();
}
