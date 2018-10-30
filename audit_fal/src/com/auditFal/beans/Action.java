package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class Action {
    private String toDo;
    private Long realClosingDate;
    private Long wantedClosingDate;
    private Long idResponsableEntity;

    public String getToDo() {
	return toDo;
    }

    public void setToDo(String toDo) {
	this.toDo = toDo;
    }

    public Long getRealClosingDate() {
	return realClosingDate;
    }

    public void setRealClosingDate(Long realClosingDate) {
	this.realClosingDate = realClosingDate;
    }

    public Long getWantedClosingDate() {
	return wantedClosingDate;
    }

    public void setWantedClosingDate(Long wantedClosingDate) {
	this.wantedClosingDate = wantedClosingDate;
    }

    public Long getIdResponsableEntity() {
	return idResponsableEntity;
    }

    public void setIdResponsableEntity(Long idResponsableEntity) {
	this.idResponsableEntity = idResponsableEntity;
    }

    public static Action parse(JSONObject jsonObject) {
	if (jsonObject != null) {
	    Action action = new Action();

	    action.setToDo((String) jsonObject.get("toDo"));
	    action.setRealClosingDate((Long) jsonObject.get("realClosingDate"));
	    action.setWantedClosingDate((Long) jsonObject.get("wantedClosingDate"));
	    action.setIdResponsableEntity((Long) jsonObject.get("idResponsableEntity"));

	    return action;
	}

	return null;
    }

    public static Action parseResultSet(ResultSet data) throws SQLException {
	Action action = new Action();

	action.setToDo(data.getString("todo"));
	action.setRealClosingDate(data.getLong("realclosingdate"));
	if (action.getRealClosingDate().longValue() == 0)
	    action.setRealClosingDate(null);

	action.setWantedClosingDate(data.getLong("wantedclosingdate"));
	action.setIdResponsableEntity(data.getLong("id_entities"));

	return action;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(Action action) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("toDo", action.getToDo());
	jsonObject.put("realClosingDate", action.getRealClosingDate());
	jsonObject.put("wantedClosingDate", action.getWantedClosingDate());
	jsonObject.put("idResponsableEntity", action.getIdResponsableEntity());

	return jsonObject;
    }
}
