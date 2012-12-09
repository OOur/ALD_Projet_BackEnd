package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ald.projet.property.Dimension;
import com.ald.projet.property.Materiaux;


@Entity
public class Sculpture extends Oeuvre{

	@Enumerated(EnumType.STRING)
	private Materiaux materiaux;

	public Sculpture(){
		
	}
	
	
	
	
	public Sculpture(Dimension dimension, boolean hasBeenReproduced,
			Artiste artiste, Integer annee, String caracteristique,
			String titre, String resume, String commentaire, String tag, Materiaux materiaux) {
		super(dimension, hasBeenReproduced, artiste, annee, caracteristique, titre,
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
