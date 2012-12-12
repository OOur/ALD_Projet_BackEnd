package com.ald.projet;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.OeuvreDAO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Conservateur;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Peinture;
import com.ald.projet.entities.Sculpture;
import com.ald.projet.property.Dimension;
import com.ald.projet.property.Materiaux;
import com.ald.projet.property.Realisation;
import com.ald.projet.property.SupportOeuvre;

public class DAOTest {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DAOTest.class);	
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();


	public final void insertPeinture(){
		try{
			
			
			Dimension dimension1 = new Dimension(10, 20, 40);
			Artiste artiste = new Artiste("puma", "guerin", "really good art");
			Conservateur conservateur = new Conservateur();
			artisteDAO.createArtiste(artiste);
			
			
			Peinture peinture = new Peinture();
			peinture.setHasBeenReproduced(false);
			peinture.setDimension(dimension1);
			peinture.setArtiste(artiste);
			peinture.setRealisation(Realisation.ACRYLIQUE);
			peinture.setSupport(SupportOeuvre.PAPIER);
			oeuvreDAO.createOeuvre(peinture);
			
			/* Met a jour l'entree dans la base */
			peinture.setAnnee(2010);
			oeuvreDAO.updateOeuvre(peinture);
			
			
			Sculpture sculpture = new Sculpture();
			sculpture.setAnnee(2015);
			sculpture.setDimension(dimension1);
			sculpture.setArtiste(artiste);
			sculpture.setHasBeenReproduced(false);
			sculpture.setMateriaux(Materiaux.ARGILE);
			oeuvreDAO.createOeuvre(sculpture);
			
			
		
			/** Le conservateur cree une nouvelle collection et y ajoute des oeuvres 
			 * Cree une collection vide en base et remplit la table intermediaire collection_oeuvre pour faire la liaison entre 
			 * les oeuvres et leur collection
			 */
			Collection collection = conservateur.createCollection();
			conservateur.addOeuvre(peinture, collection);
			conservateur.addOeuvre(sculpture, collection);
			
			
			//LOG.debug(" " +conservateur.displayCollection(collection.getId()));
			
			
			
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
			
			LOG.debug("Test findAll");
			List<Oeuvre> oeuvres = oeuvreDAO.findAll();
			
			LOG.debug("Result Size = "+ oeuvres.size());
			Assert.assertTrue("test", oeuvres.size()>0);
		}catch(RuntimeException re){
			LOG.error("Find all failed", re);
			throw re;
		}
	}
	
	
	
	
	
}

