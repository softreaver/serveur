package com.auditFal.forms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auditFal.beans.WorkStation;
import com.auditFal.dao.DAOException;
import com.auditFal.dao.DAOUtils;
import com.auditFal.dao.WorkStationDAO;

public class WorkStationForm {
	
	private WorkStationDAO workStationDAO;
	private Connection connection;
	
	public WorkStationForm(Connection connection, WorkStationDAO workStationDAO) throws SQLException {
		this.workStationDAO = workStationDAO;
		this.connection = connection;
		this.connection.setAutoCommit(false);
	}
	
	public void saveWorkStations(ArrayList<WorkStation> workStations) throws Exception {
		for(WorkStation workSation : workStations) {
			try {
				this.workStationDAO.create(this.connection, workSation);
			} catch (DAOException e) {
				this.connection.rollback();
				throw new Exception();
			}
		}
		this.connection.commit();
		DAOUtils.closeConnection(this.connection);
	}
}
