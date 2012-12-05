package com.ald.projet;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Dimension;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Peinture;
import com.ald.projet.entities.Photographie;
import com.ald.projet.property.Realisation;


public class SampleService {
	
	private static Map<Integer, Oeuvre> oeuvres = new HashMap<Integer, Oeuvre>();
			private static final Logger LOG = LoggerFactory.getLogger(SampleService.class);
			
			static { 
				
				Artiste artiste1 = new Artiste(1, "yoann", "guerin", "autodidacte");
				Dimension dimension1 = new Dimension(0, 20, 40);
				Realisation realisation1 = Realisation.ACRYLIQUE;
				
				
				Oeuvre oeuvre1 = new Photographie();
				oeuvre1.setId(1);
//				oeuvre1.setArtiste(artiste1);
//				oeuvre1.setAnnee(2002);
//				oeuvre1.setCaracteristique("Fait sur du carton");
//				oeuvre1.setCommentaire("Super bien fait");
//				oeuvre1.setHasBeenReproduced(false);
//				oeuvre1.setResume("blabla");
//				oeuvre1.setTag("tag");
//				oeuvre1.setTitre("Simba");
				oeuvres.put(oeuvre1.getId(), oeuvre1);
				
				Peinture oeuvre2 = new Peinture();
				oeuvre2.setId(2);
//				oeuvre2.setArtiste(artiste1);
//				oeuvre2.setAnnee(2002);
//				oeuvre2.setCaracteristique("Fait sur du carton");
//				oeuvre2.setCommentaire("Super bien fait");
//				oeuvre2.setHasBeenReproduced(false);
//				oeuvre2.setResume("blabla");
//				oeuvre2.setTag("tag");
//				oeuvre2.setTitre("Simba");
//				oeuvre2.setRealisation(realisation1);
				oeuvres.put(oeuvre2.getId(), oeuvre2);
				}



}
