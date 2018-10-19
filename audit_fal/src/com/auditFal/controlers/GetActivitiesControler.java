package com.auditFal.controlers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.Activity;
import com.auditFal.dao.ActivityDAO;
import com.auditFal.forms.ActivityForm;

public class GetActivitiesControler {
    @SuppressWarnings("unchecked")
    public static String getActivities(HttpServletResponse resp, Connection connection, ActivityDAO activityDAO) {
	ArrayList<Activity> allActivities;

	try {
	    PrintWriter writer = resp.getWriter();
	    ActivityForm activityForm = new ActivityForm(connection, activityDAO);
	    allActivities = activityForm.getActivities();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (Activity activity : allActivities)
		jsonResp.add(Activity.toJsonObject(activity));

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
