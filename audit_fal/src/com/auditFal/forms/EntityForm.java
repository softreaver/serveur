package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Entity;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;
import com.auditFal.dao.EntityDAO;

public class EntityForm {
    private EntityDAO entityDAO;
    private Connection connection;

    public EntityForm(Connection connection, EntityDAO entityDAO) throws Exception {
	this.entityDAO = entityDAO;
	this.connection = connection;
	this.connection.setAutoCommit(false);
    }

    public ArrayList<Entity> getEntities() throws Exception {

	try {
	    return entityDAO.getAll(connection);

	} catch (DAOException e) {
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}
    }
}
