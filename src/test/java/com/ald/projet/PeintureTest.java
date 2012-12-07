package com.ald.projet;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.PeintureDAO;
import com.ald.projet.entities.Peinture;
import com.ald.projet.property.Dimension;

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
		//seedData();
	}


//	protected void seedData() throws Exception {
//		tx.begin();
//		Connection connection = (Connection) entityManager.unwrap(java.sql.Connection.class);
//		try {
//			IDatabaseConnection dbUnitCon = new DatabaseConnection(connection);
//			dbUnitCon.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
//			IDataSet dataset;
//			FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
//			flatXmlDataSetBuilder.setColumnSensing(true);
//			InputStream in =Thread.currentThread().getContextClassLoader().getResourceAsStream("data/dataset.xml");
//			if(in !=null){
//				LOG.warn("DataSet found");
//				dataset = flatXmlDataSetBuilder.build(in);
//			} else {
//				LOG.error("DataSet not found");
//				dataset= new DefaultDataSet();
//			}
//			DatabaseOperation.REFRESH.execute(dbUnitCon, dataset);
//		} finally {
//			tx.commit();
//		}
//	}


	/************************************ Tests unitaires *****************************************/


	public final void insertPeinture(){
		LOG.info("Test insertPeinture");
		try{
			tx.begin();
			
			Dimension dimension1 = new Dimension(10, 20, 40);
			
			Peinture oeuvre2 = new Peinture();
			oeuvre2.setHasBeenReproduced(false);
			oeuvre2.setDimension(dimension1);
			entityManager.persist(oeuvre2);
			
		}catch(RuntimeException re){
			LOG.error("Find all failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
	
	@Test
	public final void FindAll(){
		LOG.info("Test findAll");
		try{
			tx.begin();
			List<Peinture> peintures = entityManager.createQuery("Select p from Peinture p").getResultList();
			LOG.debug("Result Size = "+ peintures.size());
			Assert.assertTrue("test", peintures.size()>0);
		}catch(RuntimeException re){
			LOG.error("Find all failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
	
	
	
	
	
}
