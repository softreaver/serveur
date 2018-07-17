package com.auditFal.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
	
	protected static final String FICHIER_PROPERTIES       = "/com/auditFal/dao/dao.properties";
	protected static final String PROPERTY_URL             = "url";
	protected static final String PROPERTY_DRIVER          = "driver";
	protected static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	protected static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";
	
	protected String              url;
	protected String              username;
	protected String              password;
    
	protected DAOFactory(String url, String username, String password) {
		this.url = url;
        this.username = username;
        this.password = password;
	}
	
	public abstract Connection getConnection() throws DAOFactoryException;
	
	// Getters for all DAOs instances
	public abstract BuildingDAO getBuildingDAO();
	public abstract PostDAO getPostDAO();
	public abstract WorkStationDAO getWorkStationDAO();
	public abstract ControlPointDAO getControlPointDAO();
	public abstract EntityDAO getEntotyDAO();
	public abstract ActivityDAO getActivityDAO();
	public abstract VisitDAO getVisitDAO();
	public abstract WorkSituationDAO getWorkSituationDAO();
	public abstract ActionDAO getActionDAO();
}
