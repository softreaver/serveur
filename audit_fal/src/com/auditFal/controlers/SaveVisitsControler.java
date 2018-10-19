package com.auditFal.controlers;

import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import com.auditFal.dao.VisitDAO;

public class SaveVisitsControler {
    public static String saveVisits(String body, HttpServletResponse resp, Connection connection, VisitDAO visitDAO) {

	resp.setStatus(500);

	return "";
    }
}
