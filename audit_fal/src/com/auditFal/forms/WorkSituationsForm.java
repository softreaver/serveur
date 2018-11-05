package com.auditFal.forms;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.WorkSituation;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOFactory;
import com.auditFal.dao.DAOUtils;
import com.auditFal.dao.WorkSituationDAO;

public class WorkSituationsForm {

    private WorkSituationDAO workSituationDAO;
    private Connection connection;

    public WorkSituationsForm(DAOFactory daoFactory) throws Exception {
	workSituationDAO = daoFactory.getWorkSituationDAO();
	connection = daoFactory.getConnection();
	connection.setAutoCommit(false);
    }

    public ArrayList<WorkSituation> getWorkSituations() throws Exception {
	try {
	    return workSituationDAO.getAll(connection);

	} catch (DAOException e) {
	    System.out.println(e.getMessage());
	    throw new Exception(e);
	} finally {
	    DAOUtils.closeConnection(connection);
	}
    }
}
