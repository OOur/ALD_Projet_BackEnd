package com.ald.projet.main;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
		p.setSupport(SupportOeuvre.BOIS);
		p.setRealisation(Realisation.ACRYLIQUE);
		
//		artiste.addOeuvre(p);
//		artisteDAO.createArtiste(artiste);
		
		oeuvreDAO.createOeuvre(p);
		oeuvreDAO.testT(p);

		artisteDAO.testT(artiste);
		
//		Artiste artiste2 = new Artiste("mame birame", "sene", "bon peintre");
//		Peinture p2 = new Peinture(dimension, false, artiste2, 2010, "", "La joconde", "bla", "", "", SupportOeuvre.BOIS, Realisation.ACRYLIQUE);
//		oeuvreDAO.createOeuvre(p2);
//		oeuvreDAO.testT(p2);

		Conservateur conser = new Conservateur();
		Collection c = conser.createCollection();
		conser.addCommentOeuvre(p, "blablabla");
		conser.addOeuvre(p, c);
		
		Collection c2 = conser.createCollection();
		
		

		

	}

}


