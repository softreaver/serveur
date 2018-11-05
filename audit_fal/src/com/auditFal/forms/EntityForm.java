package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.Entity;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOUtils;
import com.auditFal.dao.EntityDAO;

public class EntityForm {
    private EntityDAO entityDAO;
    private Connection connection;

    public EntityForm(DAOFactory daoFactory) throws Exception {
	entityDAO = daoFactory.getEntityDAO();
	connection = daoFactory.getConnection();
	connection.setAutoCommit(false);
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
