package com.ald.projet.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "artiste")
public class Artiste extends Personne {
	
	private String bibliographie;
	
	@OneToMany(mappedBy="artiste", orphanRemoval=true, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
	
	public Artiste(){
		
	}
	
	public Artiste( String nom, String prenom, String bibliographie) {
		super(nom, prenom);
		this.bibliographie = bibliographie;
	}

	@XmlElement
	public String getBibliographie() {
		return bibliographie;
	}

	public void setBibliographie(String bibliographie) {
		this.bibliographie = bibliographie;
	}

	@XmlElement
	public Set<Oeuvre> getOeuvres() {
		return oeuvres;
	}


	public void setOeuvres(Set<Oeuvre> oeuvres) {
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
