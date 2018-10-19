package com.auditFal.controlers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.EntitledCompany;
import com.auditFal.beans.Pair;
import com.auditFal.dao.EntitledCompanyDAO;
import com.auditFal.forms.EntitledCompanyForm;

public class SaveEntitledCompaniesControler {
    @SuppressWarnings("unchecked")
    public static String saveEntitledCompanies(String body, HttpServletResponse resp, Connection connection,
	    EntitledCompanyDAO entitledCompanyDAO) {
	ArrayList<EntitledCompany> entitledCompanies = new ArrayList<>();

	try {
	    PrintWriter writer = resp.getWriter();
	    JSONArray requestBody = new JSONArray();
	    JSONParser jsonParser = new JSONParser();
	    requestBody = (JSONArray) jsonParser.parse(body);

	    Iterator<JSONObject> iterator = requestBody.iterator();
	    while (iterator.hasNext())
		entitledCompanies.add(EntitledCompany.parse(iterator.next()));

	    EntitledCompanyForm entitledCompanyForm = new EntitledCompanyForm(connection, entitledCompanyDAO);
	    ArrayList<Pair<Long>> autoGeneratedIds = entitledCompanyForm.saveEntitledCompanies(entitledCompanies);

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
