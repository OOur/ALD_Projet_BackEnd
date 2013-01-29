package com.ald.projet.simplified;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "artistesimplifie")
public class ArtisteSimplifie {
	
	private int id;	
	private String nom;
	private String prenom;
	
	
	public ArtisteSimplifie(){
		
	}
	

	public ArtisteSimplifie(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}


	@XmlElement
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@XmlElement
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	@XmlElement
	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}