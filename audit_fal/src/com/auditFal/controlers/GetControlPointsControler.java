package com.auditFal.controlers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.ControlPoint;
import com.auditFal.beans.VisitControlPoint;
import com.auditFal.dao.ControlPointDAO;
import com.auditFal.forms.ControlPointForm;

public class GetControlPointsControler {
    public static String getControlPoints(HttpServletResponse resp, Connection connection,
	    ControlPointDAO controlPointDAO) {
	ArrayList<ControlPoint> allControlPoints;

	try {
	    PrintWriter writer = resp.getWriter();
	    ControlPointForm controlPointForm = new controlPointForm(connection, controlPointDAO);
	    allControlPoints = controlPointForm.getBuildings();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (ControlPoint controlPoint : allControlPoints)
		jsonResp.add(VisitControlPoint.toJsonObject(controlPoint));

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
