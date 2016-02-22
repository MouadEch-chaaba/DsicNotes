package com.dsic.persistance.beansPersistor;

import java.lang.reflect.Field;

import com.dsic.annotations.PrimaryKey;
import com.dsic.persistance.interfaces.IPersistentBean;

public class BeansPersistorHelper {
	
	public static boolean isReadyToDelete(IPersistentBean bean) throws IllegalArgumentException, IllegalAccessException{
		
		boolean isReady = false;
		
		Class<? extends IPersistentBean> currentBeanClass = bean.getClass();
		
		Field[] currentBeanFields = currentBeanClass.getDeclaredFields();
		
		for(Field field : currentBeanFields){
			field.setAccessible(true);
			if(!field.isAnnotationPresent(PrimaryKey.class)){
				if(field.get(bean) == null){
					isReady = true;
				}else{
					isReady = false;
					break;
				}
			}
		}
		
		return isReady;
	}
	
	public static void prepareForDeletion(IPersistentBean bean) throws IllegalArgumentException, IllegalAccessException{
		Class<? extends IPersistentBean> currentBeanClass = bean.getClass();
		
		Field[] currentBeanFields = currentBeanClass.getDeclaredFields();
		
		for(Field field : currentBeanFields){
			field.setAccessible(true);
			
			if(!field.isAnnotationPresent(PrimaryKey.class)){
				field.set(bean, null);
			}
		}
	}
	
}
