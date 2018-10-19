package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Post {
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
    public static Post parse(JSONObject jsonObject) {
	Post post = new Post();

	post.setId((Long) jsonObject.get("id"));
	post.setName((String) jsonObject.get("name"));
	post.setLowerName((String) jsonObject.get("lowerName"));
	post.setActive((Boolean) jsonObject.get("active"));

	JSONArray JSONDependencies = new JSONArray();
	ArrayList<Long> dependencies = (JSONArray) jsonObject.get("dependencies");

	Iterator<Long> iterator = JSONDependencies.iterator();
	while (iterator.hasNext())
	    dependencies.add(iterator.next());
	post.setDependencies(dependencies);

	return post;
    }

    public static Post parseResultSet(ResultSet data) throws SQLException {
	Post post = new Post();

	post.setId(data.getLong("id"));
	post.setName(data.getString("name"));
	post.setLowerName(data.getString("lowername"));
	post.setActive(data.getBoolean("active"));

	return post;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(Post post) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("id", post.getId());
	jsonObject.put("name", post.getName());
	jsonObject.put("lowerName", post.getLowerName());
	jsonObject.put("active", post.getActive());
	jsonObject.put("dependencies", post.getDependencies());

	return jsonObject;
    }
}
