package com.ald.projet.entities;

import javax.persistence.Entity;

@Entity
public class Artiste extends Personne {
	
	private String bibliographie;
	
	public Artiste(){
		
	}
	
	public Artiste(int id, String nom, String prenom, String bibliographie) {
		super(id, nom, prenom);
		this.bibliographie = bibliographie;
	}

	public String getBibliographie() {
		return bibliographie;
	}

	public void setBibliographie(String bibliographie) {
		this.bibliographie = bibliographie;
	}
	
	
	
	
}
