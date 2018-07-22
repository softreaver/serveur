package com.auditFal.beans;

import org.json.simple.JSONObject;

public class Action {
	private String toDo;
	private Integer realClosingDate;
	private Integer wantedClosingDate;
	private Long idResponsableEntity;
	
	public String getToDo() {
		return toDo;
	}
	public void setToDo(String toDo) {
		this.toDo = toDo;
	}
	public Integer getRealClosingDate() {
		return realClosingDate;
	}
	public void setRealClosingDate(Integer realClosingDate) {
		this.realClosingDate = realClosingDate;
	}
	public Integer getWantedClosingDate() {
		return wantedClosingDate;
	}
	public void setWantedClosingDate(Integer wantedClosingDate) {
		this.wantedClosingDate = wantedClosingDate;
	}
	public Long getIdResponsableEntity() {
		return idResponsableEntity;
	}
	public void setIdResponsableEntity(Long idResponsableEntity) {
		this.idResponsableEntity = idResponsableEntity;
	}
	
	public static Action parse(JSONObject jsonObject) {
		Action action = new Action();
		
		action.setToDo( (String) jsonObject.get("toDo"));
		action.setRealClosingDate( (Integer) jsonObject.get("realColsingDate"));
		action.setWantedClosingDate( (Integer) jsonObject.get("wantedClosingDate"));
		action.setIdResponsableEntity( (Long) jsonObject.get("idResponsableEntity"));
		
		return action;
	}
}
