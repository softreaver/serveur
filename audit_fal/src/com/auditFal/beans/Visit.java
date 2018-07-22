package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Visit {
	private Long number;
	private String title;
	private Integer workType;
	private String workingCompany;
	private Integer dateOfVisit;
	private Integer piNumber;
	private Boolean isPiDigital;
	private ArrayList<String> photosUrls;
	private ArrayList<ControlPoint> controlPointsList;
	private Long idUser;
	
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getWorkType() {
		return workType;
	}
	public void setWorkType(Integer workType) {
		this.workType = workType;
	}
	public String getWorkingCompany() {
		return workingCompany;
	}
	public void setWorkingCompany(String workingCompany) {
		this.workingCompany = workingCompany;
	}
	public Integer getDateOfVisit() {
		return dateOfVisit;
	}
	public void setDateOfVisit(Integer dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	public Integer getPiNumber() {
		return piNumber;
	}
	public void setPiNumber(Integer piNumber) {
		this.piNumber = piNumber;
	}
	public Boolean getIsPiDigital() {
		return isPiDigital;
	}
	public void setIsPiDigital(Boolean isPiDigital) {
		this.isPiDigital = isPiDigital;
	}
	public ArrayList<String> getPhotosUrls() {
		return photosUrls;
	}
	public void setPhotosUrls(ArrayList<String> photosUrls) {
		this.photosUrls = photosUrls;
	}
	public ArrayList<ControlPoint> getControlPointsList() {
		return controlPointsList;
	}
	public void setControlPointsList(ArrayList<ControlPoint> controlPointsList) {
		this.controlPointsList = controlPointsList;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public static Visit parse(JSONObject jsonObject) {
		Visit visit = new Visit();
		
		visit.setNumber( (Long) jsonObject.get("number") );
		visit.setTitle( (String) jsonObject.get("title") );
		visit.setWorkType( (Integer) jsonObject.get("workType") );
		visit.setWorkingCompany( (String) jsonObject.get("workingCompany") );
		visit.setDateOfVisit( (Integer) jsonObject.get("dateOfVisit") );
		visit.setPiNumber( (Integer) jsonObject.get("piNumber") );
		visit.setIsPiDigital( (Boolean) jsonObject.get("isPiDigital") );
		
		visit.setControlPointsList(ControlPoint.parseToArrayList( (JSONArray) jsonObject.get("checkedControlPointsList")) );
		
		return visit;
	}
	
	public static Visit parseResultSet(ResultSet data) throws SQLException {
		Visit visit = new Visit();
		
		visit.setNumber( data.getLong("number") );
		visit.setTitle( data.getString("title") );
		visit.setWorkType( data.getInt("worktype") );
		visit.setWorkingCompany( data.getString("workingcompany") );
		visit.setDateOfVisit( data.getInt("dateofvisit") );
		visit.setPiNumber( data.getInt("pinumber") );
		visit.setIsPiDigital( data.getBoolean("ispidigital") );
		
		return visit;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject toJsonObject(Visit visit) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("number", visit.getNumber());
		jsonObject.put("title", visit.getTitle());
		jsonObject.put("workType", visit.getWorkType());
		jsonObject.put("workingCompany", visit.getWorkingCompany());
		jsonObject.put("dateOfVisit", visit.getDateOfVisit());
		jsonObject.put("piNumber", visit.getPiNumber());
		jsonObject.put("isPiDigital", visit.getIsPiDigital());
		
		return jsonObject;
	}
}
