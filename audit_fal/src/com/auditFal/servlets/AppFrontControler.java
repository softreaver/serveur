package com.auditFal.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auditFal.controlers.GetActivitiesControler;
import com.auditFal.controlers.GetBuildingsControler;
import com.auditFal.controlers.GetControlPointsControler;
import com.auditFal.controlers.GetEntitiesControler;
import com.auditFal.controlers.GetEntitledCompaniesControler;
import com.auditFal.controlers.GetPostsControler;
import com.auditFal.controlers.GetVisitsControler;
import com.auditFal.controlers.GetWorksituationsControler;
import com.auditFal.controlers.GetWorkstationsControler;
import com.auditFal.controlers.RemoveVisitControler;
import com.auditFal.controlers.SaveActivitiesControler;
import com.auditFal.controlers.SaveBuildingsControler;
import com.auditFal.controlers.SaveEntitledCompaniesControler;
import com.auditFal.controlers.SavePostsControler;
import com.auditFal.controlers.SaveVisitsControler;
import com.auditFal.controlers.SaveWorkstationsControler;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOInitialization;

@WebServlet("/app/*")
public class AppFrontControler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String CONF_DAO_FACTORY = DAOInitialization.ATT_DAO_FACTORY;

    private DAOFactory daoFactory;

    @Override
    public void init() throws ServletException {
	daoFactory = (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String requestName = AppFrontControler.getLastURIPart(req);

	// Retrieve request's body
	String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

	System.out.println("===> [" + requestName + "] " + body);
	String response = "";

	/* Use cases */
	switch (requestName) {

	case "removeVisit":
	    RemoveVisitControler.removeVisit(body, resp, daoFactory);
	    break;

	case "saveVisit":
	    SaveVisitsControler.saveVisit(body, resp, daoFactory);
	    break;

	case "saveWorkStations":
	    response = SaveWorkstationsControler.saveWorkstations(body, resp, daoFactory);
	    break;

	case "saveBuildings":
	    response = SaveBuildingsControler.saveBuildings(body, resp, daoFactory);
	    break;

	case "savePosts":
	    response = SavePostsControler.savePosts(body, resp, daoFactory);
	    break;

	case "saveEntitledCompanies":
	    response = SaveEntitledCompaniesControler.saveEntitledCompanies(body, resp, daoFactory);
	    break;

	case "saveActivities":
	    response = SaveActivitiesControler.saveActivities(body, resp, daoFactory);
	    break;

	case "getEntitledCompanies":
	    response = GetEntitledCompaniesControler.getEntitledCompanies(resp, daoFactory);
	    break;

	case "getPosts":
	    response = GetPostsControler.getPosts(resp, daoFactory);
	    break;

	case "getEntities":
	    response = GetEntitiesControler.getEntities(resp, daoFactory);
	    break;

	case "getWorkSituations":
	    response = GetWorksituationsControler.getWorksituations(resp, daoFactory);
	    break;

	case "getControlPoints":
	    response = GetControlPointsControler.getControlPoints(resp, daoFactory);
	    break;

	case "getBuildings":
	    response = GetBuildingsControler.getBuildings(resp, daoFactory);
	    break;

	case "getActivities":
	    response = GetActivitiesControler.getActivities(resp, daoFactory);
	    break;

	case "getWorkStations":
	    response = GetWorkstationsControler.getWorkstations(resp, daoFactory);
	    break;

	case "getVisits":
	    response = GetVisitsControler.getVisits(body, resp, daoFactory);
	    break;

	default:
	    // Request is not handled
	    resp.setStatus(501);
	}

	System.out.println("<=== [Response] " + response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// Request method not allowed
	resp.setStatus(405);
    }

    private static String getLastURIPart(HttpServletRequest request) {
	String uri = request.getRequestURI();
	return uri.substring(uri.lastIndexOf('/') + 1);
    }
}
