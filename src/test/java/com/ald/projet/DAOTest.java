package com.ald.projet;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.OeuvreDAO;
import com.ald.projet.DAO.PhotoDAO;
import com.ald.projet.DAO.ReproductionDAO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Conservateur;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Peinture;
import com.ald.projet.entities.Photo;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.entities.Sculpture;
import com.ald.projet.property.Dimension;
import com.ald.projet.property.Materiaux;
import com.ald.projet.property.Realisation;
import com.ald.projet.property.SupportOeuvre;
import com.ald.projet.property.SupportReproduction;

public class DAOTest {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DAOTest.class);	
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static PhotoDAO photoDAO = new PhotoDAO();
	private static ReproductionDAO reproductionDAO = new ReproductionDAO();

	private static Dimension d;
	private static Conservateur conservateur;


	@BeforeClass
	public static void setUp() throws Exception {
		d = new Dimension(10, 20, 40);
		conservateur = new Conservateur();
	}



	
	public String httpPostRequest(Oeuvre oeuvre, String resourceURI){
		try {

			/**
			 * RESTeasy client instead of DefaultHttpClient because RESTeasy client is JAX-RS aware.
			 * To use the RESTeasy client, you construct org.jboss.resteasy.client.ClientRequest 
			 * objects and build up requests using its constructors and methods 
			 */

			JAXBContext jc = JAXBContext.newInstance(oeuvre.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			java.io.StringWriter sw = new StringWriter();
			marshaller.marshal(oeuvre, sw);

			ClientRequest request = new ClientRequest("http://localhost:8080/rest/conservateur/"+resourceURI);

			// We're posting XML and a JAXB object
			request.body("application/xml", sw.toString());

			LOG.info(sw.toString());

			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() == 200) 
			{
				String str = response.getEntity();
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}






	@Test
	public final void insertOeuvre(){

		try{

			Artiste artiste = new Artiste("puma", "guerin", "really good artiste");
			Peinture p = new Peinture(d, false, artiste, null, 2010, "", "La joconde", "lolilol", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);		
			httpPostRequest(p,"createOeuvre");
			//oeuvreDAO.createOeuvre(p);
			//Assert.assertNotSame(oeuvreDAO.findById(p.getId()),null);
		}catch(RuntimeException re){
			LOG.error("insert oeuvre failed", re);
			throw re;
		}
	}

//
//	@Test
//	public final void updateOeuvre(){
//		try{
//			Artiste artiste = new Artiste(" mame birame", "sene", "bon peintre");
//
//			Peinture p = new Peinture(d, false, artiste, null,2010, "", "sourire", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
//			oeuvreDAO.createOeuvre(p);
//			p.setAnnee(2010);
//			oeuvreDAO.updateOeuvre(p);
//			Assert.assertTrue(oeuvreDAO.findById(p.getId()).getAnnee()== 2010);
//
//		}catch(RuntimeException re){
//			LOG.error("update failed", re);
//			throw re;
//		}
//	}
//
//	@Test
//	public final void createCollection(){
//		try{
//			Artiste artiste = new Artiste("vincent", "ruffet", "bon sculpteur");
//
//			Sculpture sculpture = new Sculpture();
//			sculpture.setAnnee(2015);
//			sculpture.setDimension(d);
//			sculpture.setTitre("le penseur");
//			sculpture.setArtiste(artiste);
//			sculpture.setHasBeenReproduced(false);
//			sculpture.setMateriaux(Materiaux.ARGILE);
//			oeuvreDAO.createOeuvre(sculpture);
//
//
//
//			Collection collection = new Collection();
//			conservateur.createCollection(collection);
//			conservateur.addOeuvre(sculpture, collection);
//
//			Assert.assertTrue(collectionDAO.getOeuvresOfCollection(collection.getId()).size() == 1);
//
//			List<Oeuvre> listeOeuvre = collectionDAO.getOeuvresOfCollection(collection.getId());
//			for(Oeuvre o : listeOeuvre){
//				LOG.debug(o.toString());
//			}
//
//		}catch(RuntimeException re){
//			LOG.error("create collection failed", re);
//			throw re;
//		}
//	}
//
//
//	@Test
//	public final void insertPhotoAndReproduction(){
//		try{
//			Artiste artiste = new Artiste("charle", "henry", "pas terrible");
//
//			Peinture peinture = new Peinture(d, false, artiste, null,2005, "", "caillou", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
//			oeuvreDAO.createOeuvre(peinture);
//
//			Photo photo = new Photo(peinture,null);
//			photoDAO.createPhoto(photo);
//
//			Reproduction repro = new Reproduction(peinture, 10, SupportReproduction.CARTE);
//			reproductionDAO.createReproduction(repro);
//
//			//Assert.assertTrue(photoDAO.findById(photo.getId()).getOeuvre().getId() == peinture.getId());
//			Assert.assertTrue(reproductionDAO.findById(repro.getId()).getOeuvre().getId() == peinture.getId());
//
//		}catch(RuntimeException re){
//			LOG.error("update failed", re);
//			throw re;
//		}
//	}

}

