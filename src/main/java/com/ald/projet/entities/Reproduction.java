package com.ald.projet.entities;

public class Reproduction {

	private int id;
	private Oeuvre oeuvre;
	private int prix;
	
	
	public Reproduction(Oeuvre oeuvre) {
		super();
		this.oeuvre = oeuvre.clone();

	}
	
	
	
	
}
