package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.ald.projet.property.Connexion;


@Entity
public abstract class AgentMusee extends Personne {

	@Embedded 
	private Connexion connexion;
	
	
	public AgentMusee(){
		
	}
	
	
	public AgentMusee(String nom, String prenom, Connexion connexion) {
		super(nom, prenom);
		this.connexion = connexion;
	}


	public Connexion getConnexion() {
		return connexion;
	}


	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}


}
