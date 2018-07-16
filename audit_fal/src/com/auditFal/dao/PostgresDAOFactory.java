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
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
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

	public Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection( url, username, password );
	}
	
	
	@Override
	public BuildingDAO getBuildingDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDAO getPostDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkStationDAO getWorkStationDAO() {
		return new PostgresWorkStationDAO();
	}

	@Override
	public ControlPointDAO getControlPointDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityDAO getEntotyDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityDAO getActivityDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitDAO getVisitDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkSituationDAO getWorkSituationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionDAO getActionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
