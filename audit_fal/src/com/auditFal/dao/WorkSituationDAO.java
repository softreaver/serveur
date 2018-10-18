package com.auditFal.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.auditFal.beans.WorkSituation;

public abstract class WorkSituationDAO {

    public abstract ArrayList<WorkSituation> getAll(Connection connection) throws DAOException;
}
