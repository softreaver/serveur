package com.auditFal.controlers;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.VisitForm;

public class RemoveVisitControler {
    public static void removeVisit(String body, HttpServletResponse resp, DAOFactory daoFactory) {
	resp.setContentType("application/json; charset=utf-8");
	try {
	    JSONObject requestBody = new JSONObject();
	    JSONParser jsonParser = new JSONParser();
	    requestBody = (JSONObject) jsonParser.parse(body);

	    Long visitNumber = (Long) requestBody.get("visitNumber");

	    VisitForm visitForm = new VisitForm(daoFactory);
	    visitForm.removeVisit(visitNumber);

	} catch (Exception e) {
	    e.printStackTrace();
	    resp.setStatus(500);
	}
    }
}
