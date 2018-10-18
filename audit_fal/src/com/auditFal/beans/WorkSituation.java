package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class WorkSituation {
    private Long id;
    private String name;
    private String urlIcon;
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

    public String getUrlIcon() {
	return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
	this.urlIcon = urlIcon;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public static WorkSituation parse(JSONObject jsonObject) {
	WorkSituation workSituation = new WorkSituation();

	workSituation.setId((Long) jsonObject.get("id"));
	workSituation.setName((String) jsonObject.get("name"));
	workSituation.setUrlIcon((String) jsonObject.get("urlIcon"));
	workSituation.setActive((Boolean) jsonObject.get("active"));

	return workSituation;
    }

    public static WorkSituation parseResultSet(ResultSet data) throws SQLException {
	WorkSituation workSituation = new WorkSituation();

	workSituation.setId(data.getLong("id"));
	workSituation.setName(data.getString("name"));
	workSituation.setUrlIcon(data.getString("url_icon"));
	workSituation.setActive(data.getBoolean("active"));

	return workSituation;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(WorkSituation workSituation) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("id", workSituation.getId());
	jsonObject.put("name", workSituation.getName());
	jsonObject.put("iconUrl", workSituation.getUrlIcon());
	jsonObject.put("active", workSituation.getActive());

	return jsonObject;
    }
}
