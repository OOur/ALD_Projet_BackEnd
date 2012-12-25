package com.ald.projet.main;

import java.io.StringReader;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.OeuvreDAO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Conservateur;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Peinture;
import com.ald.projet.property.Dimension;
import com.ald.projet.property.Realisation;
import com.ald.projet.property.SupportOeuvre;

public class MainClass {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Conservateur.class);	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();

	public static void main(String[] args) {



		Dimension dimension = new Dimension(10, 20, 40);

		Artiste artiste = new Artiste("puma", "guerin", "really good artist");
		Peinture p = new Peinture();
		p.setArtiste(artiste);
		p.setDimension(dimension);
		p.setAnnee(2010);
		p.setTitre("titre");
		p.addCommentaire("pas mal");
		p.setSupport(SupportOeuvre.BOIS);
		p.setRealisation(Realisation.ACRYLIQUE);

		//		artiste.addOeuvre(p);
		//		artisteDAO.createArtiste(artiste);

		//oeuvreDAO.createOeuvre(p);
		//oeuvreDAO.testT(p);

		//artisteDAO.testT(artiste);

		Conservateur conser = new Conservateur();
		Collection c = new Collection();
		conser.createCollection(c);

		//pose probleme : Cannot instantiate abstract class or interface
		//conser.addOeuvre(p, c);
		//conser.addCommentOeuvre(p, "blablabla");

		Collection c2 = new Collection();
		conser.createCollection(c2);


		oeuvreDAO.createOeuvre(p);
		LOG.info("p = "+ p.toString());


	

		try {
			
			/**
			 * RESTeasy client instead of DefaultHttpClient because RESTeasy client is JAX-RS aware.
			 * To use the RESTeasy client, you construct org.jboss.resteasy.client.ClientRequest 
			 * objects and build up requests using its constructors and methods 
			 */
			
			JAXBContext jc = JAXBContext.newInstance(p.getClass()/*,Oeuvre.class*/);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			java.io.StringWriter sw = new StringWriter();
			marshaller.marshal(p, sw);

			ClientRequest request = new ClientRequest("http://localhost:8080/rest/conservateur/createOeuvre");

			// We're posting XML and a JAXB object
			request.body("application/xml", sw.toString());

			LOG.info(sw.toString());
			
			ClientResponse<Response> response = request.post(Response.class);

			if (response.getStatus() == 200) 
			{
				Response str = response.getEntity();
				LOG.info("ca a marché  ! = "+ str);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}






	}

}


