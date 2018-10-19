package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.ControlPoint;
import com.auditFal.dao.ControlPointDAO;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;

public class ControlPointForm {
    private ControlPointDAO controlPointDAO;
    private Connection connection;

    public ControlPointForm(Connection connection, ControlPointDAO controlPointDAO) throws Exception {
	this.controlPointDAO = controlPointDAO;
	this.connection = connection;
	this.connection.setAutoCommit(false);
    }

    public ArrayList<ControlPoint> getControlPoints() throws Exception {

	try {
	    return controlPointDAO.getAll(connection);

	} catch (DAOException e) {
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}
    }
}
