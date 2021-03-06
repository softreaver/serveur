package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.Building;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.BuildingForm;

public class GetBuildingsControler {
    @SuppressWarnings("unchecked")
    public static String getBuildings(HttpServletResponse resp, DAOFactory daoFactory) {
	ArrayList<Building> allBuildings;
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    BuildingForm buildingForm = new BuildingForm(daoFactory);
	    allBuildings = buildingForm.getBuildings();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (Building building : allBuildings)
		jsonResp.add(Building.toJsonObject(building));

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
