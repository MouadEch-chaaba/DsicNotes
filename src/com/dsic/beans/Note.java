package com.dsic.beans;

import com.dsic.annotations.PrimaryKey;
import com.dsic.annotations.TableColumnName;
import com.dsic.annotations.TableName;
import com.dsic.persistance.interfaces.IPersistentBean;

@TableName("notes")
public class Note implements IPersistentBean{
	
	@PrimaryKey(isAutoIncremented = true)
	@TableColumnName("Id")
	private int identifier;
	
	private String content;
	
	private String importance;
	
	@TableColumnName("id_user")
	private int owner;
	
	public Note(){
		
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
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
