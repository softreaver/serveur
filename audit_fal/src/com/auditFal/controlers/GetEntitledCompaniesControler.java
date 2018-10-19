package com.auditFal.controlers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.auditFal.beans.EntitledCompany;
import com.auditFal.dao.EntitledCompanyDAO;
import com.auditFal.forms.EntitledCompanyForm;

public class GetEntitledCompaniesControler {
    @SuppressWarnings("unchecked")
    public static String getEntitledCompanies(HttpServletResponse resp, Connection connection,
	    EntitledCompanyDAO entitledCompanyDAO) {
	ArrayList<EntitledCompany> allEntitledCompanies;

	try {
	    PrintWriter writer = resp.getWriter();
	    EntitledCompanyForm entitledCompanyForm = new EntitledCompanyForm(connection, entitledCompanyDAO);
	    allEntitledCompanies = entitledCompanyForm.getEntitledCompanies();

	    /*
	     * construct the response body (format : [{<Item>},{<Item2>},...])
	     */
	    JSONArray jsonResp = new JSONArray();

	    for (EntitledCompany entitledCompany : allEntitledCompanies)
		jsonResp.add(EntitledCompany.toJsonObject(entitledCompany));

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
