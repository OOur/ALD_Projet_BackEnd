package com.ald.projet;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
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
	private static CollectionDAO collectionDAO = new CollectionDAO();

	private Dimension d;
	private Artiste artiste;
	private Conservateur conservateur;


	@Before
	public void setUp() throws Exception {

		d = new Dimension(10, 20, 40);
		artiste = new Artiste("puma", "guerin", "really good art");
		artisteDAO.createArtiste(artiste);
		//en attendant de le faire en cascade
		conservateur = new Conservateur();
	}



	@Test
	public final void insertOeuvre(){
		try{
			Peinture p = new Peinture(d, false, artiste, 2010, "", "La joconde", "bla", "", "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
			oeuvreDAO.createOeuvre(p);
			Assert.assertNotSame(oeuvreDAO.findById(p.getId()),null);
		}catch(RuntimeException re){
			LOG.error("insert oeuvre failed", re);
			throw re;
		}
	}


	@Test
	public final void updateOeuvre(){
		try{

			Peinture p2 = new Peinture(d, false, artiste, 2012, "", "Nuit d'été", "bla", "", "", SupportOeuvre.CARTON, Realisation.ACRYLIQUE);
			oeuvreDAO.createOeuvre(p2);
			p2.setAnnee(2010);
			oeuvreDAO.updateOeuvre(p2);
			Assert.assertTrue(oeuvreDAO.findById(p2.getId()).getAnnee()== 2010);

		}catch(RuntimeException re){
			LOG.error("update failed", re);
			throw re;
		}
	}

	@Test
	public final void createCollection(){
		try{

			Sculpture sculpture = new Sculpture();
			sculpture.setAnnee(2015);
			sculpture.setDimension(d);
			sculpture.setArtiste(artiste);
			sculpture.setHasBeenReproduced(false);
			sculpture.setMateriaux(Materiaux.ARGILE);
			oeuvreDAO.createOeuvre(sculpture);



			/** Le conservateur cree une nouvelle collection et y ajoute une oeuvre
			 * Cree une collection vide en base et remplit la table intermediaire collection_oeuvre pour faire la liaison entre 
			 * les oeuvres et leur collection
			 */
			Collection collection = conservateur.createCollection();
			conservateur.addOeuvre(sculpture, collection);

			Assert.assertTrue(collectionDAO.getOeuvresOfCollection(collection.getId()).size() == 1);

			List<Oeuvre> listeOeuvre = collectionDAO.getOeuvresOfCollection(collection.getId());
			for(Oeuvre o : listeOeuvre){
				LOG.debug(o.toString());
			}

		}catch(RuntimeException re){
			LOG.error("create collection failed", re);
			throw re;
		}
	}

}

