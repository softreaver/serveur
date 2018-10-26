package com.auditFal.controlers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.Visit;
import com.auditFal.dao.ActionDAO;
import com.auditFal.dao.VisitControlPointDAO;
import com.auditFal.dao.VisitDAO;
import com.auditFal.forms.VisitForm;

public class GetVisitsControler {
    @SuppressWarnings("unchecked")
    public static String getVisits(String body, HttpServletResponse resp, Connection connection, VisitDAO visitDAO,
	    VisitControlPointDAO visitControlPointDAO, ActionDAO actionDAO) {

	ArrayList<Visit> allVisits;

	try {
	    PrintWriter writer = resp.getWriter();
	    VisitForm visitForm = new VisitForm(connection, visitDAO, visitControlPointDAO, actionDAO);
	    Long fromDate = null;
	    Long toDate = null;

	    try {
		// Retrieve range value
		JSONObject requestBody = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		requestBody = (JSONObject) jsonParser.parse(body);

		fromDate = (Long) requestBody.get("fromDate");
		toDate = (Long) requestBody.get("toDate");

	    } catch (Exception ignore) {
	    }

	    allVisits = visitForm.getVisits(fromDate, toDate);

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (Visit visit : allVisits)
		jsonResp.add(Visit.toJsonObject(visit));

	    /* write the response into the response body */
	    writer.print(jsonResp.toJSONString());

	    return jsonResp.toJSONString();

	} catch (Exception e) {
	    e.printStackTrace();
	    resp.setStatus(500);
	    return "";
	}
    }
}
