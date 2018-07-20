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
		Properties properties = new Properties();
		
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        PostgresDAOFactory instance = new PostgresDAOFactory( url, nomUtilisateur, motDePasse );
        return instance;
    
	}

	public Connection getConnection() throws DAOFactoryException {
		
		try {
			return DriverManager.getConnection( url, username, password );
		} catch (SQLException e) {
			throw new DAOFactoryException(e);
		}
	}
	
	
	@Override
	public BuildingDAO getBuildingDAO() {
		return new PostgresBuildingDAO();
	}

	@Override
	public PostDAO getPostDAO() {
		return new PostgresPostDAO();
	}

	@Override
	public WorkStationDAO getWorkStationDAO() {
		return new PostgresWorkStationDAO();
	}

	@Override
	public ControlPointDAO getControlPointDAO() {
		return new PostgresControlPointDAO();
	}

	@Override
	public EntityDAO getEntityDAO() {
		return new PostgresEntityDAO();
	}

	@Override
	public ActivityDAO getActivityDAO() {
		return new PostgresActivityDAO();
	}

	@Override
	public VisitDAO getVisitDAO() {
		return new PostgresVisitDAO();
	}

	@Override
	public WorkSituationDAO getWorkSituationDAO() {
		return new PostgresWorkSituationDAO();
	}

	@Override
	public ActionDAO getActionDAO() {
		return new PostgresActionDAO();
	}

	@Override
	public EntitledCompanyDAO getEntitledCompanyDAO() {
		return new PostgresEntitledCompanyDAO();
	}

}
