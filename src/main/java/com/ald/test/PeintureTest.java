package com.ald.test;

import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.PeintureDAO;
import com.mysql.jdbc.Connection;

public class PeintureTest {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PeintureTest.class);
	private static PeintureDAO peintureDAO = new PeintureDAO();
	private static EntityManager entityManager;
	private EntityTransaction tx; 


	/******************** Appelé une fois à l'initialisation du test unitaire  ****************/

	@BeforeClass
	public static void initEntityManager()throws Exception
	{
		entityManager = peintureDAO.createEntityManager();
	}

	@AfterClass
	public static void closeEntityManager()throws Exception
	{
		peintureDAO.closeEntityManager();
	}


	/******************** Appelé plusieurs fois, avant chaque test unitaire **********************/
	// Démarre une transaction qui charge la base de données d'un jeu de données pré-établi (dataset.xml)
	@Before
	public void initTransaction()throws Exception
	{
		tx = entityManager.getTransaction();
		seedData();
	}


	protected void seedData() throws Exception {
		tx.begin();
		Connection connection = (Connection) entityManager.unwrap(java.sql.Connection.class);
		try {
			IDatabaseConnection dbUnitCon = new DatabaseConnection(connection);
			dbUnitCon.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
			IDataSet dataset;
			FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
			flatXmlDataSetBuilder.setColumnSensing(true);
			InputStream in =Thread.currentThread().getContextClassLoader().getResourceAsStream("data/dataset.xml");
			if(in !=null){
				LOG.warn("DataSet found");
				dataset = flatXmlDataSetBuilder.build(in);
			} else {
				LOG.error("DataSet not found");
				dataset= new DefaultDataSet();
			}
			DatabaseOperation.REFRESH.execute(dbUnitCon, dataset);
		} finally {
			tx.commit();
		}
	}


}
