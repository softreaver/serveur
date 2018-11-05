package com.auditFal.controlers;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.Visit;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.VisitForm;

public class SaveVisitsControler {
    public static void saveVisit(String body, HttpServletResponse resp, DAOFactory daoFactory) {
	resp.setContentType("application/json; charset=utf-8");
	try {
	    JSONObject requestBody = new JSONObject();
	    JSONParser jsonParser = new JSONParser();
	    requestBody = (JSONObject) jsonParser.parse(body);

	    VisitForm visitForm = new VisitForm(daoFactory);
	    visitForm.saveVisit(Visit.parse(requestBody));
	    resp.setStatus(200);
	} catch (Exception e) {
	    e.printStackTrace();
	    resp.setStatus(500);
	}
    }
}
