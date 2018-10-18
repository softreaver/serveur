package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Pair;
import com.auditFal.beans.WorkStation;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;
import com.auditFal.dao.WorkStationDAO;

public class WorkStationForm {

    private WorkStationDAO workStationDAO;
    private Connection connection;

    public WorkStationForm(Connection connection, WorkStationDAO workStationDAO) throws Exception {
	this.workStationDAO = workStationDAO;
	this.connection = connection;
	this.connection.setAutoCommit(false);
    }

    public ArrayList<Pair<Long>> saveWorkStations(ArrayList<WorkStation> workStations) throws Exception {
	ArrayList<Pair<Long>> autoGeneratedIds = new ArrayList<>();

	try {
	    for (WorkStation workSation : workStations) {
		// Check if item already exists on DB
		WorkStation check = workStationDAO.find(connection, workSation);
		// if it does
		if (check != null) {
		    if (check.getId().longValue() != workSation.getId().longValue())
			;
		    {
			Pair<Long> pair = new Pair<>();
			/* oldId */ pair.value1 = workSation.getId();
			/* id */ pair.value2 = check.getId();
			workSation.setId(check.getId());
			autoGeneratedIds.add(pair);
		    }
		    // Update the item
		    workStationDAO.update(connection, workSation);

		    // ...otherwise insert a new element
		} else {
		    Pair<Long> pair = new Pair<>();
		    /* oldId */ pair.value1 = workSation.getId();
		    /* id */ pair.value2 = workStationDAO.create(connection, workSation);
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

    public ArrayList<WorkStation> getWorkStations() throws Exception {

	try {
	    return workStationDAO.getAll(connection);

	} catch (DAOException e) {
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}
    }
}
