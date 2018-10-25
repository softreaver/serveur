package com.auditFal.beans;

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
	    action.setRealClosingDate((Long) jsonObject.get("realColsingDate"));
	    action.setWantedClosingDate((Long) jsonObject.get("wantedClosingDate"));
	    action.setIdResponsableEntity((Long) jsonObject.get("idResponsableEntity"));

	    return action;
	}

	return null;
    }
}
