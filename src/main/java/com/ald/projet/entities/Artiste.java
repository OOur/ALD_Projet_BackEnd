package com.ald.projet.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Artiste extends Personne {
	
	private String bibliographie;
	
	@OneToMany(mappedBy="artiste")
	private List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
	
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

	public List<Oeuvre> getOeuvres() {
		return oeuvres;
	}


	public void setOeuvres(List<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;
	}
	
	
	
	
	
}
