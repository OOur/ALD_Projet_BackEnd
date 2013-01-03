package com.ald.projet.entities;

import javax.persistence.Entity;

import com.ald.projet.DAO.ReproductionDAO;
import com.ald.projet.property.Connexion;


@Entity
public class Libraire extends AgentMusee {

	private static ReproductionDAO reproductionDAO = new ReproductionDAO();
	
	
	public Libraire(String nom, String prenom, Connexion connexion) {
		super(nom, prenom, connexion);
	}

	public void fixPrice(Reproduction r, int price){
		r.setPrix(price);
		reproductionDAO.updateReproduction(r);
		
	}
}
