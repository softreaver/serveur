package com.auditFal.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDAOFactory extends DAOFactory {

    private PostgresDAOFactory(String url, String username, String password) {
	super(url, username, password);
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
	if (PostgresDAOFactory.instance == null) {
	    Properties properties = new Properties();

	    String url;
	    String driver;
	    String nomUtilisateur;
	    String motDePasse;

	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

	    if (fichierProperties == null)
		throw new DAOConfigurationException(
			"Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");

	    try {
		properties.load(fichierProperties);
		url = properties.getProperty(PROPERTY_URL);
		driver = properties.getProperty(PROPERTY_DRIVER);
		motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
	    } catch (IOException e) {
		throw new DAOConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES,
			e);
	    }

	    try {
		Class.forName(driver);
	    } catch (ClassNotFoundException e) {
		throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
	    }

	    PostgresDAOFactory.instance = new PostgresDAOFactory(url, nomUtilisateur, motDePasse);
	}

	return PostgresDAOFactory.instance;

    }

    @Override
    public Connection getConnection() throws DAOFactoryException {

	try {
	    return DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
	    throw new DAOFactoryException(e.getMessage());
	}
    }

    @Override
    public BuildingDAO getBuildingDAO() {
	if (buildingDAO == null)
	    buildingDAO = new PostgresBuildingDAO();

	return buildingDAO;
    }

    @Override
    public PostDAO getPostDAO() {
	if (postDAO == null)
	    postDAO = new PostgresPostDAO();

	return postDAO;
    }

    @Override
    public WorkStationDAO getWorkStationDAO() {
	if (workStationDAO == null)
	    workStationDAO = new PostgresWorkStationDAO();

	return workStationDAO;
    }

    @Override
    public ControlPointDAO getControlPointDAO() {
	if (controlPointDAO == null)
	    controlPointDAO = new PostgresControlPointDAO();

	return controlPointDAO;
    }

    @Override
    public EntityDAO getEntityDAO() {
	if (entityDAO == null)
	    entityDAO = new PostgresEntityDAO();

	return entityDAO;
    }

    @Override
    public ActivityDAO getActivityDAO() {
	if (activityDAO == null)
	    activityDAO = new PostgresActivityDAO();

	return activityDAO;
    }

    @Override
    public VisitDAO getVisitDAO() {
	if (visitDAO == null)
	    visitDAO = new PostgresVisitDAO();

	return visitDAO;
    }

    @Override
    public WorkSituationDAO getWorkSituationDAO() {
	if (workSituationDAO == null)
	    workSituationDAO = new PostgresWorkSituationDAO();

	return workSituationDAO;
    }

    @Override
    public ActionDAO getActionDAO() {
	if (actionDAO == null)
	    actionDAO = new PostgresActionDAO();

	return actionDAO;
    }

    @Override
    public EntitledCompanyDAO getEntitledCompanyDAO() {
	if (entitledCompanyDAO == null)
	    entitledCompanyDAO = new PostgresEntitledCompanyDAO();

	return entitledCompanyDAO;
    }

    @Override
    public VisitControlPointDAO getVisitControlPointDAO() {
	if (visitControlPointDAO == null)
	    visitControlPointDAO = new PostgresVisitControlPointDAO();

	return visitControlPointDAO;
    }

}
