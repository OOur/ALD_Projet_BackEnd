package com.ald.projet.entities;

import javax.persistence.Entity;


@Entity
public class Libraire extends AgentMusee {

	public Libraire(String nom, String prenom, String login,
			String password) {
		super(nom, prenom, login, password);
	}

	public void fixPrice(Reproduction r, int price){
		r.setPrix(price);
	}
}
