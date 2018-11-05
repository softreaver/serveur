package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.Building;
import com.auditFal.beans.Pair;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.BuildingForm;

public class SaveBuildingsControler {
    @SuppressWarnings("unchecked")
    public static String saveBuildings(String body, HttpServletResponse resp, DAOFactory daoFactory) {
	ArrayList<Building> buildings = new ArrayList<>();
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    JSONArray requestBody = new JSONArray();
	    JSONParser jsonParser = new JSONParser();
	    requestBody = (JSONArray) jsonParser.parse(body);

	    Iterator<JSONObject> iterator = requestBody.iterator();
	    while (iterator.hasNext())
		buildings.add(Building.parse(iterator.next()));

	    BuildingForm buildingForm = new BuildingForm(daoFactory);
	    ArrayList<Pair<Long>> autoGeneratedIds = buildingForm.saveBuildings(buildings);

	    /*
	     * construct the response body (format : [{"oldId":-1, "id":1},{"oldId":-2,
	     * "id":2},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (Pair<Long> line : autoGeneratedIds) {
		JSONObject jsonPair = new JSONObject();
		jsonPair.put("oldId", line.value1);
		jsonPair.put("id", line.value2);
		jsonResp.add(jsonPair);
	    }

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
