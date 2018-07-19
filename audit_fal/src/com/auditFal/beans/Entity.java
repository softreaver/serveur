package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Entity {
	private Long id;
	private String name;
	private String lowerName;
	private Boolean active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLowerName() {
		return lowerName;
	}
	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public static Entity parseResultSet(ResultSet data) throws SQLException {
		Entity entity = new Entity();
		
		entity.setId(data.getLong("id"));
		entity.setName(data.getString("name"));
		entity.setLowerName(data.getString("lowername"));
		entity.setActive(data.getBoolean("active"));
		
		return entity;
	}
}