package com.auditFal.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Visit {
    private Long number;
    private String title;
    private Long workType;
    private String workingCompany;
    private Long dateOfVisit;
    private Long piNumber;
    private Boolean isPiDigital;
    private ArrayList<String> photosUrls;
    private ArrayList<VisitControlPoint> controlPointsList;
    private Long idUser;
    private Long idEntitledCompany;
    private Long idBuilding;
    private Long idWorkStation;
    private Long idPost;
    private Long idActivity;

    public Long getWorkType() {
	return workType;
    }

    public void setWorkType(Long workType) {
	this.workType = workType;
    }

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

    public Long getIdEntitledCompany() {
	return idEntitledCompany;
    }

    public void setIdEntitledCompany(Long idEntitledCompany) {
	this.idEntitledCompany = idEntitledCompany;
    }

    public Long getIdBuilding() {
	return idBuilding;
    }

    public void setIdBuilding(Long idBuilding) {
	this.idBuilding = idBuilding;
    }

    public Long getIdWorkStation() {
	return idWorkStation;
    }

    public void setIdWorkStation(Long idWorkStation) {
	this.idWorkStation = idWorkStation;
    }

    public Long getIdPost() {
	return idPost;
    }

    public void setIdPost(Long idPost) {
	this.idPost = idPost;
    }

    public Long getIdActivity() {
	return idActivity;
    }

    public void setIdActivity(Long idActivity) {
	this.idActivity = idActivity;
    }

    public String getWorkingCompany() {
	return workingCompany;
    }

    public void setWorkingCompany(String workingCompany) {
	this.workingCompany = workingCompany;
    }

    public Long getDateOfVisit() {
	return dateOfVisit;
    }

    public void setDateOfVisit(Long dateOfVisit) {
	this.dateOfVisit = dateOfVisit;
    }

    public Long getPiNumber() {
	return piNumber;
    }

    public void setPiNumber(Long piNumber) {
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

    public ArrayList<VisitControlPoint> getControlPointsList() {
	return controlPointsList;
    }

    public void setControlPointsList(ArrayList<VisitControlPoint> controlPointsList) {
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

	visit.setNumber((Long) jsonObject.get("number"));
	visit.setTitle((String) jsonObject.get("title"));
	visit.setWorkingCompany((String) jsonObject.get("workingCompany"));
	visit.setWorkType((Long) jsonObject.get("workType"));
	visit.setDateOfVisit((Long) jsonObject.get("dateOfVisit"));
	visit.setPiNumber((Long) jsonObject.get("piNumber"));
	visit.setIsPiDigital((Boolean) jsonObject.get("isPiDigital"));
	visit.setIdEntitledCompany((Long) jsonObject.get("idEntitledCompany"));
	visit.setIdBuilding((Long) jsonObject.get("idBuilding"));
	visit.setIdWorkStation((Long) jsonObject.get("idWorkStation"));
	visit.setIdPost((Long) jsonObject.get("idPost"));
	visit.setIdActivity((Long) jsonObject.get("idActivity"));

	visit.setIdUser(null);
	visit.setPhotosUrls(new ArrayList<>());
	visit.setControlPointsList(
		VisitControlPoint.parseToArrayList((JSONArray) jsonObject.get("checkedControlPointsList")));

	return visit;
    }

    public static Visit parseResultSet(ResultSet data) throws SQLException {
	Visit visit = new Visit();

	visit.setNumber(data.getLong("number"));
	visit.setTitle(data.getString("title"));
	visit.setWorkingCompany(data.getString("workingcompany"));
	visit.setWorkType(data.getLong("worktype"));
	visit.setDateOfVisit(data.getLong("dateofvisit"));
	visit.setPiNumber(data.getLong("pinumber"));
	visit.setIsPiDigital(data.getBoolean("ispidigital"));

	return visit;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject toJsonObject(Visit visit) {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("number", visit.getNumber());
	jsonObject.put("title", visit.getTitle());
	jsonObject.put("workingCompany", visit.getWorkingCompany());
	jsonObject.put("dateOfVisit", visit.getDateOfVisit());
	jsonObject.put("piNumber", visit.getPiNumber());
	jsonObject.put("isPiDigital", visit.getIsPiDigital());
	jsonObject.put("workType", visit.getWorkType());

	return jsonObject;
    }
}
