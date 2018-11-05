package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.Visit;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.VisitForm;

public class GetVisitsControler {
    @SuppressWarnings("unchecked")
    public static String getVisits(String body, HttpServletResponse resp, DAOFactory daoFactory) {

	ArrayList<Visit> allVisits;
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    VisitForm visitForm = new VisitForm(daoFactory);
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
