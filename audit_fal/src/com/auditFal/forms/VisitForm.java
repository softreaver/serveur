package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Visit;
import com.auditFal.beans.VisitControlPoint;
import com.auditFal.dao.ActionDAO;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;
import com.auditFal.dao.VisitControlPointDAO;
import com.auditFal.dao.VisitDAO;

public class VisitForm {
    private VisitDAO visitDAO;
    private VisitControlPointDAO visitControlPointDAO;
    private ActionDAO actionDAO;
    private Connection connection;

    public VisitForm(Connection connection, VisitDAO visitDAO, VisitControlPointDAO visitControlPointDAO,
	    ActionDAO actionDAO) throws Exception {
	this.visitDAO = visitDAO;
	this.visitControlPointDAO = visitControlPointDAO;
	this.actionDAO = actionDAO;
	this.connection = connection;
	this.connection.setAutoCommit(false);
    }

    public void saveVisit(Visit visit) throws Exception {
	try {

	    // Check if item already exists on DB
	    Visit check = visitDAO.findById(connection, visit.getNumber());
	    // if it does
	    if (check != null)
		// Update the item
		visitDAO.update(connection, visit);
	    else {
		visit.setNumber(visitDAO.create(connection, visit));

		// Save all control points of the visit
		for (VisitControlPoint visitControlPoint : visit.getControlPointsList()) {
		    visitControlPointDAO.create(connection, visitControlPoint, visit.getNumber());
		    if (visitControlPoint.getAction() != null)
			actionDAO.create(connection, visitControlPoint.getAction(),
				visitControlPoint.getIdControlPoints(), visit.getNumber());
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
    }

    public ArrayList<Visit> getVisits() throws Exception {
	return getVisits(null, null);
    }

    public ArrayList<Visit> getVisits(Long fromDate, Long toDate) throws Exception {

	ArrayList<Visit> visits = new ArrayList<>();
	try {
	    visits = visitDAO.findByDate(connection, fromDate, toDate);

	    // Retrieve visit's control points
	    for (Visit visit : visits) {
		visit.setControlPointsList(visitControlPointDAO.findByVisitId(connection, visit.getNumber()));

		// Retrieve the action for each visit's control points
		for (VisitControlPoint visitControlPoint : visit.getControlPointsList())
		    visitControlPoint.setAction(actionDAO.find(connection, visitControlPoint));
	    }

	    return visits;
	} catch (DAOException e) {
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}
    }

}
