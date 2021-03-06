package com.ald.projet.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;

import com.ald.projet.property.Dimension;
import com.ald.projet.property.Materiaux;


@Entity
@XmlRootElement(name = "sculpture")
public class Sculpture extends Oeuvre{

	@Enumerated(EnumType.STRING)
	private Materiaux materiaux;

	public Sculpture(){
		
	}
	
	
	
	
	public Sculpture(Dimension dimension, boolean hasBeenReproduced,
			Artiste artiste, List<Photo> photo, Integer annee, String caracteristique,
			String titre, String resume, List<String> commentaire, String tag, Materiaux materiaux) {
		super(dimension, hasBeenReproduced, artiste, photo, annee, caracteristique, titre,
				resume, commentaire, tag);
		this.materiaux = materiaux;
	}


	public Materiaux getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(Materiaux materiaux) {
		this.materiaux = materiaux;
	}
	
	
}
