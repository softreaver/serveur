package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class Entity {
    private Long id;
    private String name;
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
	entity.setActive(data.getBoolean("active"));

	return entity;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(Entity entity) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("id", entity.getId());
	jsonObject.put("name", entity.getName());
	jsonObject.put("active", entity.getActive());

	return jsonObject;
    }
}
