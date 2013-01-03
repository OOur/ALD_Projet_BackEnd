package com.ald.projet;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;


import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Conservateur;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Peinture;
import com.ald.projet.entities.Photo;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.entities.Sculpture;
import com.ald.projet.property.Connexion;
import com.ald.projet.property.Dimension;
import com.ald.projet.property.EtatCollection;
import com.ald.projet.property.Materiaux;
import com.ald.projet.property.Realisation;
import com.ald.projet.property.SupportOeuvre;
import com.ald.projet.property.SupportReproduction;

/**
 * Les tests de DAO se font à travers les web services REST pour récupérer un entityManager valide de JPAFilter
 */

public class DAOTest {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DAOTest.class);	

	private static Dimension d;
	private static Conservateur conservateur;
	
	private static JAXBContext jc;


	@BeforeClass
	public static void setUp() throws Exception {
		d = new Dimension(10, 20, 40);
		conservateur = new Conservateur();
		jc = JAXBContext.newInstance(Oeuvre.class, Collection.class, Reproduction.class, Photo.class, Connexion.class);
	}

	/**
	 * RESTeasy client instead of DefaultHttpClient because RESTeasy client is JAX-RS aware.
	 * To use the RESTeasy client, you construct org.jboss.resteasy.client.ClientRequest 
	 * objects and build up requests using its constructors and methods 
	 */

	public Object httpPostRequest(Object o, String resourceURI){
		try {

			// sérialise l'objet pour l'envoyer via une requete POST
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			java.io.StringWriter sw = new StringWriter();
			marshaller.marshal(o, sw);

			ClientRequest request = new ClientRequest("http://localhost:8080/rest/conservateur/"+resourceURI);

			// We're posting XML and a JAXB object
			request.body("application/xml", sw.toString());
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() == 200) 
			{
				Unmarshaller un = jc.createUnmarshaller();
				Object object = (Object) un.unmarshal(new StringReader(response.getEntity()));
				LOG.info("laaaaaaa "+object.toString());
				return object;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public Object httpGetRequest(int id, String resourceURI){
		try {

			ClientRequest request = new ClientRequest("http://localhost:8080/rest/conservateur/"+resourceURI+"/"+id);

			// We're posting XML and a JAXB object
			request.accept("application/xml");
			ClientResponse<String> response = request.get(String.class);

			if (response.getStatus() == 200) 
			{
				Unmarshaller un = jc.createUnmarshaller();
				Object o = un.unmarshal(new StringReader(response.getEntity()));
				return o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public final void connexionTest(){

		try{	
			Connexion connexion = new Connexion("azerty","azerty");
			try {

				// sérialise l'objet pour l'envoyer via une requete POST
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
				java.io.StringWriter sw = new StringWriter();
				marshaller.marshal(connexion, sw);

				ClientRequest request = new ClientRequest("http://localhost:8080/rest/conservateur/connexion");

				// We're posting XML and a JAXB object
				request.body("application/xml", sw.toString());
				ClientResponse<String> response = request.post(String.class);

				if (response.getStatus() == 200) 
				{
					Unmarshaller un = jc.createUnmarshaller();
					LOG.info(response.getEntity());
				//	Assert.assertNotSame(response.getEntity(),true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		
		}catch(RuntimeException re){
			LOG.error("connexion test failed", re);
			throw re;
		}
	}
	

	@Test
	public final void insertOeuvre(){

		try{

			Artiste artiste = new Artiste("puma", "guerin", "really good artiste");
			Peinture p = new Peinture(d, false, artiste, null, 2010, "", "La joconde", "lolilol", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);		
			Oeuvre peintureVerif = (Oeuvre) httpPostRequest(p,"createOeuvre");
			Assert.assertNotSame(peintureVerif,null);
		}catch(RuntimeException re){
			LOG.error("insert oeuvre failed", re);
			throw re;
		}
	}

	@Test
	public final void updateOeuvre(){
		try{
			Artiste artiste = new Artiste(" mame birame", "sene", "bon peintre");

			Peinture p = new Peinture(d, false, artiste, null,2080, "", "sourire", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
			Oeuvre peintureReturn =(Oeuvre) httpPostRequest(p,"createOeuvre");

			//attention il faut modifier peintureReturn now et laisser p de coté, si on modifie p (qui n'est pas managé) cela aura pour effet
			// de créer une nouvelle entité (merge) au lieu de mettre à jour
			peintureReturn.setAnnee(2012);
			Oeuvre peintureReturn2 = (Oeuvre) httpPostRequest(peintureReturn,"updateOeuvre");
			Assert.assertTrue(peintureReturn2.getAnnee()== 2012);


		}catch(RuntimeException re){
			LOG.error("update failed", re);
			throw re;
		}
	}

	@Test
	public final void createCollection(){
		try{
			Artiste artiste = new Artiste("vincent", "ruffet", "bon sculpteur");

			Peinture p = new Peinture(d, false, artiste, null,2014, "", "test", "test", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);

			Sculpture sculpture = new Sculpture();
			sculpture.setAnnee(2566469);
			sculpture.setDimension(d);
			sculpture.setTitre("le penseur");
			sculpture.setArtiste(artiste);
			sculpture.setHasBeenReproduced(false);
			sculpture.setMateriaux(Materiaux.ARGILE);

			Sculpture sculptureReturn = (Sculpture) httpPostRequest(sculpture,"createOeuvre");

			Collection collection = new Collection();
			collection.setEtat(EtatCollection.EXPOSED);
			//ajout d'une peinture en cascade
			collection.addOeuvre(p);
			Collection collectionReturn = (Collection) httpPostRequest(collection,"createCollection");			

			//idem que pour le updateOeuvre plus haut
			//ajout d'une sculpture déjà existante
			collectionReturn.addOeuvre(sculptureReturn);
			collectionReturn.addComment("blavljh");

			Collection collectionReturn2 =(Collection) httpPostRequest(collectionReturn,"updateCollection");
			Assert.assertTrue(collectionReturn2.getOeuvres().size() == 2);

		}catch(RuntimeException re){
			LOG.error("create collection failed", re);
			throw re;
		}
	}

	@Test
	public final void insertPhotoAndReproduction(){
		try{
			Artiste artiste = new Artiste("charle", "henry", "pas terrible");

			Peinture peinture = new Peinture(d, false, artiste, null,2005, "", "caillou", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);	
			Peinture peintureReturn = (Peinture) httpPostRequest(peinture,"createOeuvre");

			Photo photo = new Photo("/le/chemin/absolue", peintureReturn);
			Photo photoReturn = (Photo) httpPostRequest(photo,"createPhoto");

			Reproduction repro = new Reproduction(peintureReturn, 10, SupportReproduction.CARTE);
			Reproduction reproReturn = (Reproduction) httpPostRequest(repro,"createReproduction");		

			Assert.assertTrue(reproReturn.getOeuvre().getId() == peintureReturn.getId());

		}catch(RuntimeException re){
			LOG.error("update failed", re);
			throw re;
		}
	}
}