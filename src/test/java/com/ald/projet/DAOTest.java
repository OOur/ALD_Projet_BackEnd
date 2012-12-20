package com.ald.projet;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
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

	private static Dimension d;
	private static Conservateur conservateur;


	@BeforeClass
	public static void setUp() throws Exception {
		d = new Dimension(10, 20, 40);
		conservateur = new Conservateur();
	}



	@Test
	public final void insertOeuvre(){

		try{

			Artiste artiste = new Artiste("puma", "guerin", "really good artiste");
			Peinture p = new Peinture(d, false, artiste, 2010, "", "La joconde", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
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
			Artiste artiste = new Artiste(" mame birame", "sene", "bon peintre");
			
			Peinture p = new Peinture(d, false, artiste, 2010, "", "sourire", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
			oeuvreDAO.createOeuvre(p);
			p.setAnnee(2010);
			oeuvreDAO.updateOeuvre(p);
			Assert.assertTrue(oeuvreDAO.findById(p.getId()).getAnnee()== 2010);

		}catch(RuntimeException re){
			LOG.error("update failed", re);
			throw re;
		}
	}

	@Test
	public final void createCollection(){
		try{
			Artiste artiste = new Artiste("vincent", "guerin", "bon sculpteur");
			
			Sculpture sculpture = new Sculpture();
			sculpture.setAnnee(2015);
			sculpture.setDimension(d);
			sculpture.setTitre("le penseur");
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

