package com.ald.projet.entities;

import javax.persistence.Entity;

@Entity
public class Artiste extends Personne {
	
	private String bibliographie;
	
	public Artiste(){
		
	}
	
	public Artiste( String nom, String prenom, String bibliographie) {
		super(nom, prenom);
		this.bibliographie = bibliographie;
	}

	public String getBibliographie() {
		return bibliographie;
	}

	public void setBibliographie(String bibliographie) {
		this.bibliographie = bibliographie;
	}
	
	
	
	
}
