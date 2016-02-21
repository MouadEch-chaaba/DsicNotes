package com.dsic.persistance;

import java.lang.reflect.Field;

import com.dsic.annotations.PrimaryKey;
import com.dsic.annotations.TableColumnName;
import com.dsic.annotations.TableName;
import com.dsic.persistance.interfaces.IPersistentBean;
import com.dsic.persistance.interfaces.IQueryGenerator;

public class UpdateGenerator implements IQueryGenerator {
	
	private static UpdateGenerator instance = null;
	
	private IPersistentBean currentBean;
	
	private String finalUpdateQuery = null;
	
	private UpdateGenerator() {
		
	}
	
	public static UpdateGenerator getInstance(){
		return instance!=null ? instance : (instance = new UpdateGenerator());
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
		sb.append("UPDATE "+tableName+" SET ");
		
		// Appending the table fields and there values
		
		String whereClause = "WHERE ";
		
		for(Field field : currentBeanFields){
			// Modifying the field access to be accessible
			field.setAccessible(true);
			
			/* If the field represents a primary key so we need to append
			 * it's name and value to the whereClause
			 */
			if(field.isAnnotationPresent(PrimaryKey.class)){
				if(field.isAnnotationPresent(TableColumnName.class)){
					// Testing if the field is numeric
					if(field.getType().isAssignableFrom(int.class) || field.getType().isAssignableFrom(float.class) || field.getType().isAssignableFrom(double.class)){
						whereClause += field.getAnnotation(TableColumnName.class).value() + " = "+field.get(this.currentBean)+";";
					}else{
						whereClause += field.getAnnotation(TableColumnName.class).value() + " = '"+field.get(this.currentBean)+"';";
					}
				}else{
					// Testing if the field is numeric
					if(field.getType().isAssignableFrom(int.class) || field.getType().isAssignableFrom(float.class) || field.getType().isAssignableFrom(double.class)){
						whereClause += field.getName() + " = "+field.get(this.currentBean)+";";
					}else{
						whereClause += field.getName() + " = '"+field.get(this.currentBean)+"';";
					}
				}
			}else{
				if(field.isAnnotationPresent(TableColumnName.class)){
					if(field.getType().isAssignableFrom(int.class) || field.getType().isAssignableFrom(float.class) || field.getType().isAssignableFrom(double.class)){
						sb.append(field.getAnnotation(TableColumnName.class).value()+" = "+field.get(this.currentBean)+",");
					}else{
						sb.append(field.getAnnotation(TableColumnName.class).value()+" = '"+field.get(this.currentBean)+"',");
					}
				}else{
					if(field.getType().isAssignableFrom(int.class) || field.getType().isAssignableFrom(float.class) || field.getType().isAssignableFrom(double.class)){
						sb.append(field.getName()+" = "+field.get(this.currentBean)+",");
					}else{
						sb.append(field.getName()+" = '"+field.get(this.currentBean)+"',");
					}
				}
			}
		}
		
		// cleaning and Appending the where clause to the query
		sb.setCharAt(sb.lastIndexOf(","), ' ');
		sb.append(whereClause);
		
		// Storing the final Query
		this.finalUpdateQuery = sb.toString();
	}

	@Override
	public String getQuery() {
		return this.finalUpdateQuery;
	}
}