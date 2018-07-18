package com.auditFal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.auditFal.beans.Building;
import com.auditFal.beans.Pair;
import com.auditFal.beans.Post;
import com.auditFal.beans.WorkStation;
import com.auditFal.dao.BuildingDAO;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOInitialization;
import com.auditFal.dao.PostDAO;
import com.auditFal.dao.WorkStationDAO;
import com.auditFal.forms.BuildingForm;
import com.auditFal.forms.PostForm;
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
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestName = AppFrontControler.getLastURIPart(req);
		DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY);
		Connection connection = daoFactory.getConnection();

		//Retrieve request's body
		String body = req.getReader().lines()
				.reduce("", (accumulator, actual) -> accumulator + actual);
		
		JSONParser jsonParser = new JSONParser();
		PrintWriter writer = resp.getWriter();
		JSONArray requestBody = new JSONArray();
		
		/* Use cases */
		switch(requestName) {
		
/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
|||||||||||||||||||                  SAVE WORKSTATIONS                      |||||||||||||||||||||||||||||||||||||||||||||||||||||||
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/	
			case "saveWorkStations":
				ArrayList<WorkStation> workStations = new ArrayList<>();
				
				try {
					requestBody = (JSONArray) jsonParser.parse(body);
					
					Iterator<JSONObject> iterator = requestBody.iterator();
					while (iterator.hasNext()) {
						workStations.add(WorkStation.parse(iterator.next()));
					}
					
					WorkStationForm workStationForm = new WorkStationForm(connection, workStationDAO);
					ArrayList<Pair<Long>> autoGeneratedIds = workStationForm.saveWorkStations(workStations);
					
					/* construct the response body (format : [{"oldId":-1, "id":1},{"oldId":-2, "id":2},...]) */
					JSONArray jsonResp = new JSONArray();
					
					for(Pair<Long> line : autoGeneratedIds) {
						JSONObject jsonPair = new JSONObject();
						jsonPair.put("oldId", line.value1);
						jsonPair.put("id", line.value2);
						jsonResp.add(jsonPair);
					}
					
					/* write the response into the response body */
					writer.print(jsonResp.toJSONString());
					
				} catch (Exception e) {
					e.printStackTrace();
					writer.print(e.getMessage());
					resp.setStatus(500);
				}
				
				break;
				
/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
|||||||||||||||||||                  SAVE BUILDINGS                    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/
			case "saveBuildings":
				ArrayList<Building> buildings = new ArrayList<>();
				
				try {
					requestBody = (JSONArray) jsonParser.parse(body);
					
					Iterator<JSONObject> iterator = requestBody.iterator();
					while (iterator.hasNext()) {
						buildings.add(Building.parse(iterator.next()));
					}
					
					BuildingForm buildingForm = new BuildingForm(connection, buildingDAO);
					ArrayList<Pair<Long>> autoGeneratedIds = buildingForm.saveBuildings(buildings);
					
					/* construct the response body (format : [{"oldId":-1, "id":1},{"oldId":-2, "id":2},...]) */
					JSONArray jsonResp = new JSONArray();
					
					for(Pair<Long> line : autoGeneratedIds) {
						JSONObject jsonPair = new JSONObject();
						jsonPair.put("oldId", line.value1);
						jsonPair.put("id", line.value2);
						jsonResp.add(jsonPair);
					}
					
					/* write the response into the response body */
					writer.print(jsonResp.toJSONString());
					
				} catch (Exception e) {
					e.printStackTrace();
					writer.print(e.getMessage());
					resp.setStatus(500);
				}
				
				break;

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
|||||||||||||||||||                  SAVE POSTS                   |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/
			case "savePosts":
				ArrayList<Post> posts = new ArrayList<>();
				
				try {
					requestBody = (JSONArray) jsonParser.parse(body);
					
					Iterator<JSONObject> iterator = requestBody.iterator();
					while (iterator.hasNext()) {
						posts.add(Post.parse(iterator.next()));
					}
					
					PostForm postForm = new PostForm(connection, postDAO);
					ArrayList<Pair<Long>> autoGeneratedIds = postForm.savePosts(posts);
					
					/* construct the response body (format : [{"oldId":-1, "id":1},{"oldId":-2, "id":2},...]) */
					JSONArray jsonResp = new JSONArray();
					
					for(Pair<Long> line : autoGeneratedIds) {
						JSONObject jsonPair = new JSONObject();
						jsonPair.put("oldId", line.value1);
						jsonPair.put("id", line.value2);
						jsonResp.add(jsonPair);
					}
					
					/* write the response into the response body */
					writer.print(jsonResp.toJSONString());
					
				} catch (Exception e) {
					e.printStackTrace();
					writer.print(e.getMessage());
					resp.setStatus(500);
				}
				
				break;
				

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
|||||||||||||||||||                  BAD REQUESTS                   |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/				
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
