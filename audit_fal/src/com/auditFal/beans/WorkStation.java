package com.auditFal.beans;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WorkStation {
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
	public static WorkStation parse(JSONObject jsonObject) {
		WorkStation workStation = new WorkStation();
		
		workStation.setId( (Long) jsonObject.get("id") );
		workStation.setName( (String) jsonObject.get("name") );
		workStation.setLowerName( (String) jsonObject.get("lowerName") );
		workStation.setActive( (Boolean) jsonObject.get("active") );
		
		JSONArray JSONDependencies = new JSONArray();
		ArrayList<Long> dependencies = (JSONArray) jsonObject.get("dependencies");
		
		Iterator<Long> iterator = JSONDependencies.iterator();
		while (iterator.hasNext()) {
			dependencies.add(iterator.next());
		}
		workStation.setDependencies(dependencies);
		
		return workStation;
	}
}

