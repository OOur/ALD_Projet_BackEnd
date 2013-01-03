package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ald.projet.property.Connexion;
import com.ald.projet.property.Status;


@Entity
@XmlRootElement(name = "employe")
public class Employe  {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nom;
	
	private String prenom;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Embedded 
	private Connexion connexion;
	
	
	public Employe(){
		
	}
	
	
	public Employe(Status status, String nom, String prenom, Connexion connexion) {
		this.nom = nom;
		this.prenom = prenom;
		this.connexion = connexion;
		this.status = status;
	}


	@XmlElement
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public Connexion getConnexion() {
		return connexion;
	}


	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
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


	@XmlElement
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


}
