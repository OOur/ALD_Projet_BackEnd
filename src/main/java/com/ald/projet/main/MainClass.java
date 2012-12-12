package com.ald.projet.main;

import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.OeuvreDAO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Conservateur;
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
		
		Dimension dimension1 = new Dimension(10, 20, 40);
			
			Artiste artiste = new Artiste("puma", "guerin", "prodTest");
			artisteDAO.createArtiste(artiste);

			Peinture peinture = new Peinture();
			peinture.setTitre("PRODTEST");
			peinture.setHasBeenReproduced(false);
			peinture.setDimension(dimension1);
			peinture.setArtiste(artiste);
			peinture.setRealisation(Realisation.ACRYLIQUE);
			peinture.setSupport(SupportOeuvre.PAPIER);
			oeuvreDAO.createOeuvre(peinture);
			
			Conservateur conser = new Conservateur();
			Collection c = conser.createCollection();
			Collection c2 = conser.createCollection();

		}

	}


