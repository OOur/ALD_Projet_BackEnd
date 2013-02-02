package com.ald.projet;

import java.io.StringReader;
import java.io.StringWriter;

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
import com.ald.projet.entities.Employe;
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
import com.ald.projet.property.Status;
import com.ald.projet.property.SupportOeuvre;
import com.ald.projet.property.SupportReproduction;


/**
 * Les tests de DAO se font à travers les web services REST pour récupérer un entityManager valide de JPAFilter
 */

public class DAOTest {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DAOTest.class);	

	private static Dimension d;

	private static JAXBContext jc;


	@BeforeClass
	public static void setUp() throws Exception {
		d = new Dimension(10, 20, 40);
		jc = JAXBContext.newInstance(Oeuvre.class, Collection.class, Reproduction.class, Photo.class, Connexion.class, Employe.class);
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

			ClientRequest request = new ClientRequest("http://localhost:8080/rest/service/"+resourceURI);

			// We're posting XML and a JAXB object
			request.body("application/xml", sw.toString());
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() == 200) 
			{
				Unmarshaller un = jc.createUnmarshaller();
				Object object = (Object) un.unmarshal(new StringReader(response.getEntity()));
				return object;
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
			Employe employe = new Employe(Status.CONSERVATEUR, "Ben", "Stiler", connexion);
			httpPostRequest(employe,"employe/create");

			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			java.io.StringWriter sw = new StringWriter();
			marshaller.marshal(connexion, sw);

			ClientRequest request = new ClientRequest("http://localhost:8080/rest/service/connexion");
			request.body("application/xml", sw.toString());
			LOG.info(sw.toString());
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() == 200) 
			{
				LOG.info(response.getEntity());
				Assert.assertTrue(!response.getEntity().isEmpty());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("connexion test failed", e);
		}

	}


	@Test
	public final void insertOeuvre(){

		try{

			Artiste artiste = new Artiste("puma", "guerin", "really good artiste");
			Artiste artisteVerif = (Artiste) httpPostRequest(artiste,"artiste/create");

			Peinture p = new Peinture(d, false, artisteVerif, null, 11111, "", "La joconde", "lolilol", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);		
			Oeuvre peintureVerif = (Oeuvre) httpPostRequest(p,"oeuvre/create");
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
			Artiste artisteVerif = (Artiste) httpPostRequest(artiste,"artiste/create");

			Peinture p = new Peinture(d, false, artisteVerif, null,2080, "", "sourire", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
			Oeuvre peintureReturn =(Oeuvre) httpPostRequest(p,"oeuvre/create");

			//attention il faut modifier peintureReturn now et laisser p de coté, si on modifie p (qui n'est pas managé) cela aura pour effet
			// de créer une nouvelle entité (merge) au lieu de mettre à jour
			peintureReturn.setAnnee(2012);
			Oeuvre peintureReturn2 = (Oeuvre) httpPostRequest(peintureReturn,"oeuvre/update");
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
			Artiste artisteVerif = (Artiste) httpPostRequest(artiste,"artiste/create");

			Peinture p = new Peinture(d, false, artisteVerif, null,2014, "", "test", "test", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
			Oeuvre peintureReturn =(Oeuvre) httpPostRequest(p,"oeuvre/create");
			
			Sculpture sculpture = new Sculpture();
			sculpture.setAnnee(2566469);
			sculpture.setDimension(d);
			sculpture.setTitre("le penseur");
			sculpture.setArtiste(artisteVerif);
			sculpture.setHasBeenReproduced(false);
			sculpture.setMateriaux(Materiaux.ARGILE);

			Sculpture sculptureReturn = (Sculpture) httpPostRequest(sculpture,"oeuvre/create");

			Collection collection = new Collection();
			collection.setLibele("art moderne");
			collection.setEtat(EtatCollection.EXPOSED);
			//ajout d'une peinture en cascade
			collection.addOeuvre(peintureReturn);
			Collection collectionReturn = (Collection) httpPostRequest(collection,"collection/create");			

			//idem que pour le updateOeuvre plus haut
			//ajout d'une sculpture déjà existante
			collectionReturn.addOeuvre(sculptureReturn);
			collectionReturn.addComment("blavljh");

			Collection collectionReturn2 =(Collection) httpPostRequest(collectionReturn,"collection/update");
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
			Artiste artisteVerif = (Artiste) httpPostRequest(artiste,"artiste/create");

			Peinture peinture = new Peinture(d, false, artisteVerif, null,2005, "", "caillou", "bla", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);	
			Peinture peintureReturn = (Peinture) httpPostRequest(peinture,"oeuvre/create");

			Photo photo = new Photo("/le/chemin/absolue", peintureReturn);
			Photo photoReturn = (Photo) httpPostRequest(photo,"photo/create");

			Reproduction repro = new Reproduction(peintureReturn, 10, SupportReproduction.CARTE);
			Reproduction reproReturn = (Reproduction) httpPostRequest(repro,"reproduction/create");		

			Assert.assertTrue(reproReturn.getOeuvre().getId() == peintureReturn.getId());

		}catch(RuntimeException re){
			LOG.error("update failed", re);
			throw re;
		}
	}


	/**
	 * Cette méthode retourne toutes les oeuvres selon les critères sélectionnés par l'utilisateur de l'appli.
	 * Ici il demande à obtenir toutes les oeuvres qui ont été faites à une année donnée et un artiste particulier.
	 * ==> Seul l'attribut "année" et "artiste" de l'Oeuvre o a été renseignés, tous les autres champs sont à null.
	 * On va donc faire une recherche sur les années et les artistes
	 * @throws Exception 
	 */
	@Test
	public final void criteriaTest() throws Exception{

		try{

			Artiste artiste = new Artiste("puma", "guerin", "really good artiste");
			Artiste artisteVerif = (Artiste) httpPostRequest(artiste,"artiste/create");

			Peinture p = new Peinture(d, false, artisteVerif, null, 123456, "", "La joconde", "lolilol", null, "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);		
			Oeuvre peintureVerif = (Oeuvre) httpPostRequest(p,"oeuvre/create");
			Assert.assertNotSame(peintureVerif,null);

			Oeuvre o = new Peinture();
			o.setAnnee(123456);
			o.setArtiste(artisteVerif);

			
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			java.io.StringWriter sw = new StringWriter();
			marshaller.marshal(o, sw);
			
			ClientRequest request = new ClientRequest("http://localhost:8080/rest/service/oeuvres/criteria");
			request.body("application/xml", sw.toString());
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() == 200) 
			{
				LOG.info(response.getEntity());
				Assert.assertTrue(!response.getEntity().isEmpty());
			}
			
			
//			List<Oeuvre> oeuvres = (List<Oeuvre>) httpPostRequest(o,"oeuvres/criteria");
//			Assert.assertTrue(oeuvres.size() >= 1);
//			LOG.info("SIZE = "+ oeuvres.size());


		}catch(RuntimeException re){
			LOG.error("criteria failed", re);
			throw re;
		}
	}


}