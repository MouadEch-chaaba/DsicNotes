package com.dsic.persistance.beansPersistor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import com.dsic.annotations.PrimaryKey;
import com.dsic.connections.ConnectionManager;
import com.dsic.persistance.interfaces.IPersistentBean;
import com.dsic.persistance.interfaces.IQueryGenerator;
import com.dsic.persistance.queryGenerators.DeleteGenerator;
import com.dsic.persistance.queryGenerators.InsertGenarator;
import com.dsic.persistance.queryGenerators.UpdateGenerator;

public class BeansPersistor {
	
	private static BeansPersistor instance = null;
	
	private IQueryGenerator currentGenerator;
	
	private IPersistentBean currentBean;
	
	private BeansPersistor(){
		
	}
	
	public static BeansPersistor getInstance(){
		return instance != null ? instance : (instance = new BeansPersistor());
	}
	
	private void loadGenerator(String action){
		
		switch (action) {
			case "insert":
				this.currentGenerator = InsertGenarator.getInstance();
			break;
			
			case "update":
				this.currentGenerator = UpdateGenerator.getInstance();
			break;
				
			case "delete":
				this.currentGenerator = DeleteGenerator.getInstance();
			break;
		}
	}
	
	public void takeCareOf(IPersistentBean bean) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		this.currentBean = bean;
		
		String action = null;
		
		/*
		 * If the bean's primary key field is not set => it's an insert action.
		 * If the bean's primary key field is set and all other field are not => it's
		 * a delete action.
		 * Otherwise it's an update action
		 */
		
		// Storing the bean's Class object in order to use Object Reflection on it.
		Class<? extends IPersistentBean> currentBeanClass = bean.getClass();
		
		// Getting the bean's fields
		Field[] currentBeanFields = currentBeanClass.getDeclaredFields();
		
		
		for(Field field : currentBeanFields){
			// Modifying the field access to be accessible
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(PrimaryKey.class)){
				// checking if bean's primary key field is not set
				if(field.get(this.currentBean) == null){
					action = "insert";
				}
				// Checking if the bean's primary key field is set and all other field are not
				else if(field.get(this.currentBean) != null && BeansPersistorHelper.isReadyToDelete(this.currentBean)){
					action = "delete";
				}
				// Otherwise
				else{
					action = "update";
				}
				break;
			}
		}
		
		// Loading the Appropriate Generator
		this.loadGenerator(action);
		
		// Getting a connection to the Database
		Connection connection = ConnectionManager.getConnection();
		
		// Executing the Appropriate Query
		connection.createStatement().executeQuery(this.currentGenerator.getQuery());
	}
	
}
