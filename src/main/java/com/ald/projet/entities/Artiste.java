package com.ald.projet.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Artiste extends Personne {
	
	private String bibliographie;
	
	@OneToMany(mappedBy="artiste", orphanRemoval=true, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
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
	
	
	//methode metier qui evite d'avoir a faire : artiste.getOeuvres().add(oeuvre) et artiste.setOeuvres(oeuvres)
	public void addOeuvre(Oeuvre o){
		this.oeuvres.add(o);
	}
	
	public void removeOeuvre(Oeuvre o){
		this.oeuvres.remove(o);
	}
	
	
	
	
	
}
