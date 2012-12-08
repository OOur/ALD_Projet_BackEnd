package com.ald.projet.entities;

import javax.persistence.Entity;

import com.ald.projet.DAO.ReproductionDAO;


@Entity
public class Libraire extends AgentMusee {

	private static ReproductionDAO reproductionDAO = new ReproductionDAO();
	
	
	public Libraire(String nom, String prenom, String login,
			String password) {
		super(nom, prenom, login, password);
	}

	public void fixPrice(Reproduction r, int price){
		r.setPrix(price);
		reproductionDAO.updateReproduction(r);
		
	}
}
