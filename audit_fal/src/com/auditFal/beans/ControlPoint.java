package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class ControlPoint {
    private Long id;
    private Long idWorkSituation;
    private String name;
    private Integer defaultRiskFactor;
    private String underSituation;
    private String preventiveMeasure;
    private Boolean active;

    /**
     * GETTERS / SETTERS
     */
    public Long getId() {
	return id;
    }

    public Long getIdWorkSituation() {
	return idWorkSituation;
    }

    public void setIdWorkSituation(Long idWorkSituation) {
	this.idWorkSituation = idWorkSituation;
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

    public Integer getDefaultRiskFactor() {
	return defaultRiskFactor;
    }

    public void setDefaultRiskFactor(Integer defaultRiskFactor) {
	this.defaultRiskFactor = defaultRiskFactor;
    }

    public String getUnderSituation() {
	return underSituation;
    }

    public void setUnderSituation(String underSituation) {
	this.underSituation = underSituation;
    }

    public String getPreventiveMeasure() {
	return preventiveMeasure;
    }

    public void setPreventiveMeasure(String preventiveMeasure) {
	this.preventiveMeasure = preventiveMeasure;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    /**
     * METHODES
     */

    public static ControlPoint parseResultSet(ResultSet data) throws SQLException {
	ControlPoint controlPoint = new ControlPoint();

	controlPoint.setId(data.getLong("id"));
	controlPoint.setName(data.getString("name"));
	controlPoint.setDefaultRiskFactor(data.getInt("defaultriskfactor"));
	controlPoint.setIdWorkSituation(data.getLong("id_worksituations"));
	controlPoint.setUnderSituation(data.getString("undersituation"));
	controlPoint.setPreventiveMeasure(data.getString("preventivemeasure"));
	controlPoint.setActive(data.getBoolean("active"));

	return controlPoint;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(ControlPoint controlPoint) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("id", controlPoint.getId());
	jsonObject.put("name", controlPoint.getName());
	jsonObject.put("defaultRiskFactor", controlPoint.getDefaultRiskFactor());
	jsonObject.put("idWorkSituation", controlPoint.getIdWorkSituation());
	jsonObject.put("underSituation", controlPoint.getUnderSituation());
	jsonObject.put("preventiveMeasure", controlPoint.getPreventiveMeasure());
	jsonObject.put("active", controlPoint.getActive());

	return jsonObject;
    }

}
