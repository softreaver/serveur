package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Building {
    private Long id;
    private String name;
    private String lowerName;
    private Boolean active;
    private ArrayList<Long> dependencies;

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

    public ArrayList<Long> getDependencies() {
	return dependencies;
    }

    public void setDependencies(ArrayList<Long> dependencies) {
	this.dependencies = dependencies;
    }

    @SuppressWarnings("unchecked")
    public static Building parse(JSONObject jsonObject) {
	Building building = new Building();

	building.setId((Long) jsonObject.get("id"));
	building.setName((String) jsonObject.get("name"));
	building.setLowerName((String) jsonObject.get("lowerName"));
	building.setActive((Boolean) jsonObject.get("active"));

	JSONArray JSONDependencies = new JSONArray();
	ArrayList<Long> dependencies = (JSONArray) jsonObject.get("dependencies");

	Iterator<Long> iterator = JSONDependencies.iterator();
	while (iterator.hasNext())
	    dependencies.add(iterator.next());
	building.setDependencies(dependencies);

	return building;
    }

    public static Building parseResultSet(ResultSet data) throws SQLException {
	Building building = new Building();

	building.setId(data.getLong("id"));
	building.setName(data.getString("name"));
	building.setLowerName(data.getString("lowername"));
	building.setActive(data.getBoolean("active"));

	return building;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(Building building) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("id", building.getId());
	jsonObject.put("name", building.getName());
	jsonObject.put("lowerName", building.getLowerName());
	jsonObject.put("active", building.getActive());
	jsonObject.put("dependencies", building.getDependencies());

	return jsonObject;
    }
}
