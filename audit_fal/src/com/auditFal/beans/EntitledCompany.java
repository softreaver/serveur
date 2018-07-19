package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class EntitledCompany {
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
	
	public static EntitledCompany parse(JSONObject jsonObject) {
		EntitledCompany entitledCompany = new EntitledCompany();
		
		entitledCompany.setId( (Long) jsonObject.get("id") );
		entitledCompany.setName( (String) jsonObject.get("name") );
		entitledCompany.setLowerName( (String) jsonObject.get("lowerName") );
		entitledCompany.setActive( (Boolean) jsonObject.get("active") );
		
		return entitledCompany;
	}
	
	public static EntitledCompany parseResultSet(ResultSet data) throws SQLException {
		EntitledCompany entitledCompany = new EntitledCompany();
		
		entitledCompany.setId(data.getLong("id"));
		entitledCompany.setName(data.getString("name"));
		entitledCompany.setLowerName(data.getString("lowername"));
		entitledCompany.setActive(data.getBoolean("active"));
		
		return entitledCompany;
	}
}
