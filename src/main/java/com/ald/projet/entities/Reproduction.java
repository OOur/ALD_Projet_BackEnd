package com.ald.projet.entities;

public class Reproduction {

	private int id;
	private Oeuvre oeuvre;
	private int prix;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Oeuvre getOeuvre() {
		return oeuvre;
	}


	public void setOeuvre(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}


	public int getPrix() {
		return prix;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public Reproduction(Oeuvre oeuvre) {
		super();
		this.oeuvre = oeuvre.clone();

	}
	
	
	
	
}
