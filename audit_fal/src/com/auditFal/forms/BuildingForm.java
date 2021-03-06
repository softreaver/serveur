package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Building;
import com.auditFal.beans.Pair;
import com.auditFal.dao.BuildingDAO;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOUtils;

public class BuildingForm {
    private BuildingDAO buildingDAO;
    private Connection connection;

    public BuildingForm(DAOFactory daoFactory) throws Exception {
	buildingDAO = daoFactory.getBuildingDAO();
	connection = daoFactory.getConnection();
	connection.setAutoCommit(false);
    }

    public ArrayList<Pair<Long>> saveBuildings(ArrayList<Building> buildings) throws Exception {
	ArrayList<Pair<Long>> autoGeneratedIds = new ArrayList<>();

	try {
	    for (Building building : buildings) {
		// Check if item already exists on DB
		Building check = buildingDAO.find(connection, building);
		// if it does
		if (check != null) {
		    if (check.getId().longValue() != building.getId().longValue())
			;
		    {
			Pair<Long> pair = new Pair<>();
			/* oldId */ pair.value1 = building.getId();
			/* id */ pair.value2 = check.getId();
			building.setId(check.getId());
			autoGeneratedIds.add(pair);
		    }
		    // Update the item
		    buildingDAO.update(connection, building);

		    // ...otherwise insert a new element
		} else {
		    Pair<Long> pair = new Pair<>();
		    /* oldId */ pair.value1 = building.getId();
		    /* id */ pair.value2 = buildingDAO.create(connection, building);
		    autoGeneratedIds.add(pair);
		}
	    }

	    connection.commit();

	} catch (DAOException e) {
	    connection.rollback();
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}

	return autoGeneratedIds;
    }

    public ArrayList<Building> getBuildings() throws Exception {

	try {
	    return buildingDAO.getAll(connection);

	} catch (DAOException e) {
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}
    }
}
