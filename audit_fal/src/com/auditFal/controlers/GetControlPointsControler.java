package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.ControlPoint;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.ControlPointForm;

public class GetControlPointsControler {
    @SuppressWarnings("unchecked")
    public static String getControlPoints(HttpServletResponse resp, DAOFactory daoFactory) {
	ArrayList<ControlPoint> allControlPoints;
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    ControlPointForm controlPointForm = new ControlPointForm(daoFactory);
	    allControlPoints = controlPointForm.getControlPoints();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (ControlPoint controlPoint : allControlPoints)
		jsonResp.add(ControlPoint.toJsonObject(controlPoint));

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
