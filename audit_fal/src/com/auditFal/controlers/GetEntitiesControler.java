package com.auditFal.controlers;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.Entity;
import com.auditFal.dao.DAOFactory;
import com.auditFal.forms.EntityForm;

public class GetEntitiesControler {
    @SuppressWarnings("unchecked")
    public static String getEntities(HttpServletResponse resp, DAOFactory daoFactory) {
	ArrayList<Entity> allEntities;
	resp.setContentType("application/json; charset=utf-8");

	try {
	    PrintWriter writer = resp.getWriter();
	    EntityForm entityForm = new EntityForm(daoFactory);
	    allEntities = entityForm.getEntities();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (Entity entity : allEntities)
		jsonResp.add(Entity.toJsonObject(entity));

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
