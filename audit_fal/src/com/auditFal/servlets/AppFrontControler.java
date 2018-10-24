package com.auditFal.servlets;

import java.io.IOException;
import java.sql.Connection;

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
import com.auditFal.controlers.GetWorksituationsControler;
import com.auditFal.controlers.GetWorkstationsControler;
import com.auditFal.controlers.SaveActivitiesControler;
import com.auditFal.controlers.SaveBuildingsControler;
import com.auditFal.controlers.SaveEntitledCompaniesControler;
import com.auditFal.controlers.SavePostsControler;
import com.auditFal.controlers.SaveVisitsControler;
import com.auditFal.controlers.SaveWorkstationsControler;
import com.auditFal.dao.ActionDAO;
import com.auditFal.dao.ActivityDAO;
import com.auditFal.dao.BuildingDAO;
import com.auditFal.dao.ControlPointDAO;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOInitialization;
import com.auditFal.dao.EntitledCompanyDAO;
import com.auditFal.dao.EntityDAO;
import com.auditFal.dao.PostDAO;
import com.auditFal.dao.VisitControlPointDAO;
import com.auditFal.dao.VisitDAO;
import com.auditFal.dao.WorkSituationDAO;
import com.auditFal.dao.WorkStationDAO;

@WebServlet("/app/*")
public class AppFrontControler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String CONF_DAO_FACTORY = DAOInitialization.ATT_DAO_FACTORY;

    private VisitControlPointDAO visitControlPointDAO;
    private EntitledCompanyDAO entitledCompanyDAO;
    private WorkSituationDAO workSituationDAO;
    private ControlPointDAO controlPointDAO;
    private WorkStationDAO workStationDAO;
    private BuildingDAO buildingDAO;
    private ActivityDAO activityDAO;
    private ActionDAO actionDAO;
    private EntityDAO entityDAO;
    private VisitDAO visitDAO;
    private PostDAO postDAO;

    @Override
    public void init() throws ServletException {
	visitControlPointDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY))
		.getVisitControlPointDAO();
	entitledCompanyDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEntitledCompanyDAO();
	workSituationDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getWorkSituationDAO();
	controlPointDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getControlPointDAO();
	workStationDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getWorkStationDAO();
	buildingDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBuildingDAO();
	activityDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getActivityDAO();
	actionDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getActionDAO();
	entityDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEntityDAO();
	visitDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVisitDAO();
	postDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPostDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String requestName = AppFrontControler.getLastURIPart(req);

	// Open connection with DB
	DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY);
	Connection connection = daoFactory.getConnection();

	// Retrieve request's body
	String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

	System.out.println("===> [" + requestName + "] " + body);

	resp.setContentType("application/json; charset=utf-8");

	String response = "";

	/* Use cases */
	switch (requestName) {

	/*
	 * ////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| SAVE WORKSTATIONS
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "saveWorkStations":
	    response = SaveWorkstationsControler.saveWorkstations(body, resp, connection, workStationDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| SAVE BUILDINGS
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "saveBuildings":
	    response = SaveBuildingsControler.saveBuildings(body, resp, connection, buildingDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| SAVE POSTS
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "savePosts":
	    response = SavePostsControler.savePosts(body, resp, connection, postDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| SAVE ENTITLED COMPANIES
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "saveEntitledCompanies":
	    response = SaveEntitledCompaniesControler.saveEntitledCompanies(body, resp, connection, entitledCompanyDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| SAVE ACTIVITIES
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "saveActivities":
	    response = SaveActivitiesControler.saveActivities(body, resp, connection, activityDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET ENTITLED COMPANIES
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getEntitledCompanies":
	    response = GetEntitledCompaniesControler.getEntitledCompanies(resp, connection, entitledCompanyDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET POSTS
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getPosts":
	    response = GetPostsControler.getPosts(resp, connection, postDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET ENTITIES
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getEntities":
	    response = GetEntitiesControler.getEntities(resp, connection, entityDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET WORK SITUATIONS
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getWorkSituations":
	    response = GetWorksituationsControler.getWorksituations(resp, connection, workSituationDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET CONTROL POINTS
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getControlPoints":
	    response = GetControlPointsControler.getControlPoints(resp, connection, controlPointDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET BUILDINGS
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getBuildings":
	    response = GetBuildingsControler.getBuildings(resp, connection, buildingDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET ACTIVITIES
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getActivities":
	    response = GetActivitiesControler.getActivities(resp, connection, activityDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| GET WORKSTATIONS
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "getWorkStations":
	    response = GetWorkstationsControler.getWorkstations(resp, connection, workStationDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| SAVE VISITS
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	case "saveVisit":
	    SaveVisitsControler.saveVisit(body, resp, connection, visitDAO, visitControlPointDAO, actionDAO);
	    break;

	/*
	 * /////////////////////////////////////////////////////////////////////
	 * ////////////////////////////////////////////////////////////
	 * ||||||||||||||||||| BAD REQUESTS
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
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
