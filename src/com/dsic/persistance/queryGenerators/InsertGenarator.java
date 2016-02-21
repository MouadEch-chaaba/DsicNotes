package com.dsic.persistance.queryGenerators;

import java.lang.reflect.Field;

import com.dsic.annotations.PrimaryKey;
import com.dsic.annotations.TableColumnName;
import com.dsic.annotations.TableName;
import com.dsic.persistance.interfaces.IPersistentBean;
import com.dsic.persistance.interfaces.IQueryGenerator;

public class InsertGenarator implements IQueryGenerator {
	
	private static InsertGenarator instance = null;
	
	private IPersistentBean currentBean;
	
	private String finalInsertQuery = null;
	
	private InsertGenarator() {
		
	}
	
	public static InsertGenarator getInstance(){
		return instance!=null ? instance : (instance = new InsertGenarator());
	}
	
	@Override
	public void prepareQueryFor(IPersistentBean bean) throws IllegalArgumentException, IllegalAccessException {
		this.currentBean = bean;
		
		this.executeGeneration();
	}
	
	private void executeGeneration() throws IllegalArgumentException, IllegalAccessException{
		// Getting the currentBean Class object in order to use Object Reflection
		Class<? extends IPersistentBean> currentBeanClass = this.currentBean.getClass();
		
		// Extracting the tableName
		String tableName = null;
		
		/* 
		 * If the TableName annotation is present so the table name in the
		 * final insert query will be the value of that annotation.
		 * Else the table name in the final query will be the bean class name.
		 *  
		 */
		if(currentBeanClass.isAnnotationPresent(TableName.class))
			tableName = currentBeanClass.getAnnotation(TableName.class).value();
		else
			tableName = currentBeanClass.getName();
		
		// Extracting the fields
		Field[] currentBeanFields = currentBeanClass.getDeclaredFields();
		
		// Beginning the final query generation
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+tableName+"(");
		
		// Appending the table fields
		for(Field field : currentBeanFields){
			/* If the field is primary key and auto incremented, there is no need
			 * to add it with the fields in the query.
			 * Else we need to add it with the other fields
			 */
			
			if(field.isAnnotationPresent(PrimaryKey.class)){
				if(!field.getAnnotation(PrimaryKey.class).isAutoIncremented()){
					/*
					 * If the TableColumnName annotation is present so the name of the field
					 * is the value of the annotation.
					 * Else the name of the field will be the Bean's attribute name
					 */
					if(field.isAnnotationPresent(TableColumnName.class))
						sb.append(field.getAnnotation(TableColumnName.class).value()+",");
					else
						sb.append(field.getName()+",");
				}
			}else{
				if(field.isAnnotationPresent(TableColumnName.class))
					sb.append(field.getAnnotation(TableColumnName.class).value()+",");
				else
					sb.append(field.getName()+",");
			}
		}
		
		// All table fields are added => cleaning the query
		sb.setCharAt(sb.lastIndexOf(","), ')');
		
		// Appending the fields values
		sb.append(" VALUES(");
		
		for(Field field : currentBeanFields){
			// Modifying the field access to be accessible
			field.setAccessible(true);
			
			/* If the field is primary key and auto incremented, there is no need
			 * to add a value for it in the query.
			 */
			
			if(field.isAnnotationPresent(PrimaryKey.class)){
				if(!field.getAnnotation(PrimaryKey.class).isAutoIncremented()){
					// If the field value is numeric
					if(field.getType().isAssignableFrom(int.class) || field.getType().isAssignableFrom(float.class) || field.getType().isAssignableFrom(double.class)){
						sb.append(field.get(this.currentBean)+",");
					}else{
						// If the field value is not numeric, we need to insert it between ' '
						sb.append("'"+field.get(this.currentBean)+"',");
					}
				}	
			}else{
				// If the field value is numeric
				if(field.getType().isAssignableFrom(int.class) || field.getType().isAssignableFrom(float.class) || field.getType().isAssignableFrom(double.class)){
					sb.append(field.get(this.currentBean)+",");
				}else{
					// If the field value is not numeric, we need to insert it between ' '
					sb.append("'"+field.get(this.currentBean)+"',");
				}
			}
			
				
		}
		
		// All table fields values are added => cleaning the query
		sb.setCharAt(sb.lastIndexOf(","), ')');
		sb.append(";");
		
		// Storing the final Query
		this.finalInsertQuery = sb.toString();
	}

	@Override
	public String getQuery() {
		return this.finalInsertQuery;
	}
}