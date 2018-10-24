package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class VisitControlPoint {
    private Long idVisits;
    private Long idControlPoints;
    private Long idEntities;
    private Integer conformity;
    private String commentary;
    private Action action = null;

    public Long getIdVisits() {
	return idVisits;
    }

    public void setIdVisits(Long idVisits) {
	this.idVisits = idVisits;
    }

    public Long getIdControlPoints() {
	return idControlPoints;
    }

    public void setIdControlPoints(Long idControlPoints) {
	this.idControlPoints = idControlPoints;
    }

    public Long getIdEntities() {
	return idEntities;
    }

    public void setIdEntities(Long idEntities) {
	this.idEntities = idEntities;
    }

    public Action getAction() {
	return action;
    }

    public void setAction(Action action) {
	this.action = action;
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
	while (iterator.hasNext())
	    controlPointsList.add(VisitControlPoint.parse(iterator.next()));

	return controlPointsList;
    }

    public static VisitControlPoint parse(JSONObject jsonObject) {
	VisitControlPoint controlPoint = new VisitControlPoint();

	controlPoint.setIdControlPoints((Long) jsonObject.get("id"));
	controlPoint.setIdEntities((Long) jsonObject.get("idEntity"));
	controlPoint.setConformity((Integer) jsonObject.get("conformity"));
	controlPoint.setCommentary((String) jsonObject.get("commentary"));
	controlPoint.setAction(Action.parse((JSONObject) jsonObject.get("action")));

	return controlPoint;
    }

    public static VisitControlPoint parseResultSet(ResultSet data) throws SQLException {
	VisitControlPoint visitControlPoint = new VisitControlPoint();

	visitControlPoint.setIdVisits(data.getLong("id_visits"));
	visitControlPoint.setIdControlPoints(data.getLong("id_controlpoints"));
	visitControlPoint.setIdEntities(data.getLong("id_entities"));
	visitControlPoint.setConformity(data.getInt("conformity"));
	visitControlPoint.setCommentary(data.getString("commentary"));

	return visitControlPoint;
    }
}
