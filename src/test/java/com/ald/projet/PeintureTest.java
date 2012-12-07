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
	


	public final void insertPeinture(){
		LOG.info("Test insertPeinture");
		try{
			
			
			Dimension dimension1 = new Dimension(10, 20, 40);
			
			Peinture oeuvre2 = new Peinture();
			oeuvre2.setHasBeenReproduced(false);
			oeuvre2.setDimension(dimension1);
			peintureDAO.createPeinture(oeuvre2);
			
		}catch(RuntimeException re){
			LOG.error("Find all failed", re);
			throw re;
		}
	}
	
	@Test
	public final void FindAll(){
		LOG.info("Test findAll");
		try{
			
			insertPeinture();
			List<Peinture> peintures = peintureDAO.findAll();
			LOG.debug("Result Size = "+ peintures.size());
	//		Assert.assertTrue("test", peintures.size()>0);
		}catch(RuntimeException re){
			LOG.error("Find all failed", re);
			throw re;
		}
	}
	
	
	
	
	
}
