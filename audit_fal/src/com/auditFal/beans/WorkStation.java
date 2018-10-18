package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class WorkStation {
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

    public static WorkStation parse(JSONObject jsonObject) {
	WorkStation workStation = new WorkStation();

	workStation.setId((Long) jsonObject.get("id"));
	workStation.setName((String) jsonObject.get("name"));
	workStation.setLowerName((String) jsonObject.get("lowerName"));
	workStation.setActive((Boolean) jsonObject.get("active"));

	return workStation;
    }

    public static WorkStation parseResultSet(ResultSet data) throws SQLException {
	WorkStation workStation = new WorkStation();

	workStation.setId(data.getLong("id"));
	workStation.setName(data.getString("name"));
	workStation.setLowerName(data.getString("lowername"));
	workStation.setActive(data.getBoolean("active"));

	return workStation;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(WorkStation workStation) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("id", workStation.getId());
	jsonObject.put("name", workStation.getName());
	jsonObject.put("lowerName", workStation.getLowerName());
	jsonObject.put("active", workStation.getActive());
	jsonObject.put("dependencies", new ArrayList<>());

	return jsonObject;
    }
}
