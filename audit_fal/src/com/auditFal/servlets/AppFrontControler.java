package com.auditFal.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auditFal.beans.WorkStation;
import com.auditFal.dao.BuildingDAO;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOInitialization;
import com.auditFal.dao.PostDAO;
import com.auditFal.dao.WorkStationDAO;
import com.auditFal.forms.WorkStationForm;

@WebServlet("/app/*")
public class AppFrontControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = DAOInitialization.ATT_DAO_FACTORY;

    private WorkStationDAO 	workStationDAO;
    private BuildingDAO 	buildingDAO;
    private PostDAO 		postDAO;

	@Override
	public void init() throws ServletException {
		this.workStationDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getWorkStationDAO();
		this.buildingDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBuildingDAO();
		this.postDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPostDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestName = AppFrontControler.getLastURIPart(req);
		DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY);
		Connection connection = null;
		WorkStationForm workStationForm = null;
		
		try {
			connection = daoFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(requestName) {
			case "saveWorkStations":
				ArrayList<WorkStation> workStations = new ArrayList<WorkStation>();
				WorkStation workStation = new WorkStation();
				workStation.setName("Test");
				workStations.add(workStation);
				try {
					workStationForm = new WorkStationForm(connection, workStationDAO);
					try {
						workStationForm.saveWorkStations(workStations);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case "saveBuildings":
				
				break;
			case "savePosts":
				
				break;
			default:
				// Request is not handled
				resp.setStatus(501);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Request method not allowed
		//resp.setStatus(405);
		
		//TODO remove under this line
		this.doPost(req, resp);
	}
	
	private static String getLastURIPart(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf('/') + 1);
	}
}
