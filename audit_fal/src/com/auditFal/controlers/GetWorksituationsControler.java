package com.auditFal.controlers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.WorkSituation;
import com.auditFal.dao.WorkSituationDAO;
import com.auditFal.forms.WorkSituationsForm;

public class GetWorksituationsControler {
    @SuppressWarnings("unchecked")
    public static String getWorksituations(HttpServletResponse resp, Connection connection,
	    WorkSituationDAO workSituationDAO) {
	ArrayList<WorkSituation> allWorkSituations;

	try {
	    PrintWriter writer = resp.getWriter();
	    WorkSituationsForm workSituationsForm = new WorkSituationsForm(connection, workSituationDAO);
	    allWorkSituations = workSituationsForm.getWorkSituations();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (WorkSituation workSituation : allWorkSituations)
		jsonResp.add(WorkSituation.toJsonObject(workSituation));

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
