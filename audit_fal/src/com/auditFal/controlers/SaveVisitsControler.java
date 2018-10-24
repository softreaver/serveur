package com.auditFal.controlers;

import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.Visit;
import com.auditFal.dao.ActionDAO;
import com.auditFal.dao.VisitControlPointDAO;
import com.auditFal.dao.VisitDAO;
import com.auditFal.forms.VisitForm;

public class SaveVisitsControler {
    public static void saveVisit(String body, HttpServletResponse resp, Connection connection, VisitDAO visitDAO,
	    VisitControlPointDAO visitControlPointDAO, ActionDAO actionDAO) {
	try {
	    JSONObject requestBody = new JSONObject();
	    JSONParser jsonParser = new JSONParser();
	    requestBody = (JSONObject) jsonParser.parse(body);

	    VisitForm visitForm = new VisitForm(connection, visitDAO, visitControlPointDAO, actionDAO);
	    visitForm.saveVisit(Visit.parse(requestBody));
	    resp.setStatus(200);
	} catch (Exception e) {
	    e.printStackTrace();
	    resp.setStatus(500);
	}
    }
}
