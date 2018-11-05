package com.auditFal.dao;

import java.sql.Connection;

public abstract class DAOFactory {

    protected static final String FICHIER_PROPERTIES = "/com/auditFal/dao/dao.properties";
    protected static final String PROPERTY_URL = "url";
    protected static final String PROPERTY_DRIVER = "driver";
    protected static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    protected static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

 // @formatter:off
    protected static DAOFactory instance 		= null;

    protected BuildingDAO buildingDAO 			= null;
    protected PostDAO postDAO 				= null;
    protected WorkSituationDAO workSituationDAO 	= null;
    protected ControlPointDAO controlPointDAO 		= null;
    protected EntityDAO entityDAO 			= null;
    protected ActivityDAO activityDAO 			= null;
    protected VisitDAO visitDAO 			= null;
    protected WorkStationDAO workStationDAO 		= null;
    protected ActionDAO actionDAO 			= null;
    protected EntitledCompanyDAO entitledCompanyDAO 	= null;
    protected VisitControlPointDAO visitControlPointDAO	= null;
    // @formatter:on

    protected String url;
    protected String username;
    protected String password;

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

    public abstract EntityDAO getEntityDAO();

    public abstract ActivityDAO getActivityDAO();

    public abstract VisitDAO getVisitDAO();

    public abstract WorkSituationDAO getWorkSituationDAO();

    public abstract ActionDAO getActionDAO();

    public abstract EntitledCompanyDAO getEntitledCompanyDAO();

    public abstract VisitControlPointDAO getVisitControlPointDAO();
}
