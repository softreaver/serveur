package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class Activity {
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
	
	public static Activity parse(JSONObject jsonObject) {
		Activity activity = new Activity();
		
		activity.setId( (Long) jsonObject.get("id") );
		activity.setName( (String) jsonObject.get("name") );
		activity.setLowerName( (String) jsonObject.get("lowerName") );
		activity.setActive( (Boolean) jsonObject.get("active") );
		
		return activity;
	}
	
	public static Activity parseResultSet(ResultSet data) throws SQLException {
		Activity activity = new Activity();
		
		activity.setId(data.getLong("id"));
		activity.setName(data.getString("name"));
		activity.setLowerName(data.getString("lowername"));
		activity.setActive(data.getBoolean("active"));
		
		return activity;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject toJsonObject(Activity activity) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("id", activity.getId());
		jsonObject.put("name", activity.getName());
		jsonObject.put("lowerName", activity.getLowerName());
		jsonObject.put("active", activity.getActive());
		
		return jsonObject;
	}
}
