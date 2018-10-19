package com.auditFal.beans;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class VisitControlPoint {
	private Long id;
	private Integer conformity;
	private String commentary;
	private Action action;
	
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getConformity() {
		return conformity;
	}
	public void setConformity(Integer conformity) {
		this.conformity = conformity;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	
	public static ArrayList<VisitControlPoint> parseToArrayList(JSONArray jsonArray) {
		ArrayList<VisitControlPoint> controlPointsList = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
        	controlPointsList.add(VisitControlPoint.parse(iterator.next()));
        }
        
        return controlPointsList;
	}
	
	public static VisitControlPoint parse(JSONObject jsonObject) {
		VisitControlPoint controlPoint = new VisitControlPoint();
		
		controlPoint.setId( (Long) jsonObject.get("id"));
		controlPoint.setConformity( (Integer) jsonObject.get("conformity"));
		controlPoint.setCommentary( (String) jsonObject.get("commentary"));
		controlPoint.setAction(Action.parse( (JSONObject) jsonObject.get("action")));
		
		return controlPoint;
	}
}
