package com.auditFal.beans;

import java.util.ArrayList;

public class WorkStation {
	private Long id;
	private String name;
	private String lowerName;
	private Boolean active;
	private ArrayList<Integer> dependencies;
	
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
	public ArrayList<Integer> getDependencies() {
		return dependencies;
	}
	public void setDependencies(ArrayList<Integer> dependencies) {
		this.dependencies = dependencies;
	}
}

