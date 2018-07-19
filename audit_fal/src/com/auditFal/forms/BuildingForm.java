package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Building;
import com.auditFal.beans.Pair;
import com.auditFal.dao.BuildingDAO;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;

public class BuildingForm {
	private BuildingDAO buildingDAO;
	private Connection connection;
	
	public BuildingForm(Connection connection, BuildingDAO buildingDAO) throws Exception {
		this.buildingDAO = buildingDAO;
		this.connection = connection;
		this.connection.setAutoCommit(false);
	}
	
	public ArrayList<Pair<Long>> saveBuildings(ArrayList<Building> buildings) throws Exception {
		ArrayList<Pair<Long>> autoGeneratedIds = new ArrayList<Pair<Long>>();
		
		try {
			for(Building building : buildings) {
				// Check if item already exists on DB
				Building check = this.buildingDAO.find(connection, building);
				// if it does
				if(check != null) {
					if(check.getId().longValue() != building.getId().longValue()); {
						Pair<Long> pair = new Pair<>();
						/* oldId */	pair.value1 = building.getId();
						/* id */	pair.value2 = check.getId();
						building.setId(check.getId());
						autoGeneratedIds.add(pair);
					}
					//Update the item
					this.buildingDAO.update(this.connection, building);
					
					//...otherwise insert a new element
				} else {
					Pair<Long> pair = new Pair<>();
					/* oldId */	pair.value1 = building.getId();
					/* id */	pair.value2 = this.buildingDAO.create(this.connection, building);
					autoGeneratedIds.add(pair);	
				}
			}
			
			this.connection.commit();
			
		} catch (DAOException e) {
			this.connection.rollback();
			System.out.println(e.getMessage());
			throw new Exception(e);
		} finally {
			DAOUtils.closeConnection(this.connection);
		}		
		
		return autoGeneratedIds;
	}
}