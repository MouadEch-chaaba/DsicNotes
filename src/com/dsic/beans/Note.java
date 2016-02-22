package com.dsic.beans;

import com.dsic.annotations.PrimaryKey;
import com.dsic.annotations.TableColumnName;
import com.dsic.annotations.TableName;
import com.dsic.persistance.interfaces.IPersistentBean;

@TableName("notes")
public class Note implements IPersistentBean{
	
	@PrimaryKey(isAutoIncremented = true)
	@TableColumnName("Id")
	private Integer identifier;
	
	private String content;
	
	private String importance;
	
	@TableColumnName("id_user")
	private int owner;
	
	@TableColumnName("createdAt")
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Note(){
		
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	
	
	
}
