package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Action;
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
	    if (check != null) {
		// Update the item
		visitDAO.update(connection, visit);
		updateVisitControlPoints(visit.getControlPointsList(), visit.getNumber());
	    } else {
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

    public void removeVisit(Long visitNumber) throws Exception {
	try {
	    visitControlPointDAO.deleteByVisitNumber(connection, visitNumber);
	    actionDAO.deleteByVisitNumber(connection, visitNumber);
	    visitDAO.deleteByVisitNumber(connection, visitNumber);
	} catch (DAOException e) {
	    connection.rollback();
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    connection.commit();
	    DAOUtils.closeConnection(connection);
	}
    }

    private void updateVisitControlPoints(ArrayList<VisitControlPoint> visitControlPoints, Long visitNumber)
	    throws Exception {
	ArrayList<VisitControlPoint> visitControlPointsToRemove = new ArrayList<>();
	ArrayList<VisitControlPoint> visitControlPointsToKeep = new ArrayList<>();
	ArrayList<Long> controlPointsIDs = new ArrayList<>();

	// Find all visit's control points on DB
	ArrayList<VisitControlPoint> visitControlPointsOnDB = visitControlPointDAO.findByVisitId(connection,
		visitNumber);
	for (VisitControlPoint visitControlPoint : visitControlPoints)
	    controlPointsIDs.add(visitControlPoint.getIdControlPoints());

	// Find wich visit's control point is not present in the received visit
	for (VisitControlPoint visitControlPoint : visitControlPointsOnDB)
	    if (!controlPointsIDs.contains(visitControlPoint.getIdControlPoints().longValue()))
		visitControlPointsToRemove.add(visitControlPoint);
	    else
		visitControlPointsToKeep.add(visitControlPoint);

	// Remove from DB visit's control points not used any more
	for (VisitControlPoint visitControlPoint : visitControlPointsToRemove)
	    try {
		visitControlPointDAO.delete(connection, visitControlPoint, visitNumber);
		actionDAO.delete(connection, visitControlPoint.getIdControlPoints(), visitNumber);
	    } catch (Exception ignore) {
	    }

	controlPointsIDs = new ArrayList<>();
	for (VisitControlPoint visitControlPoint : visitControlPointsToKeep)
	    controlPointsIDs.add(visitControlPoint.getIdControlPoints());

	// Add new visit control points and actions
	for (VisitControlPoint visitControlPoint : visitControlPoints)
	    if (controlPointsIDs.contains(visitControlPoint.getIdControlPoints().longValue())) {
		visitControlPointDAO.update(connection, visitControlPoint, visitNumber);
		Action action = visitControlPoint.getAction();
		Long controlPointId = visitControlPoint.getIdControlPoints();
		actionDAO.update(connection, action, controlPointId, visitNumber);
	    } else {
		visitControlPointDAO.create(connection, visitControlPoint, visitNumber);
		Action action = visitControlPoint.getAction();
		Long controlPointId = visitControlPoint.getIdControlPoints();
		if (action != null)
		    actionDAO.create(connection, action, controlPointId, visitNumber);
	    }

    }
}
