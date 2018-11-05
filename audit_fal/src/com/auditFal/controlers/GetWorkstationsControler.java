package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.WorkStation;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.WorkStationForm;

public class GetWorkstationsControler {
    @SuppressWarnings("unchecked")
    public static String getWorkstations(HttpServletResponse resp, DAOFactory daoFactory) {
	ArrayList<WorkStation> allWorkStations;
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    WorkStationForm workStationForm = new WorkStationForm(daoFactory);
	    allWorkStations = workStationForm.getWorkStations();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (WorkStation workStation : allWorkStations)
		jsonResp.add(WorkStation.toJsonObject(workStation));

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
