package com.ald.projet.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ald.projet.property.SupportReproduction;

public class Reproduction {

	@Id @GeneratedValue
	private int id;
	@OneToOne
	@JoinColumn(name="oeuvre_reproduced", nullable=false)
	private Oeuvre oeuvre;
	private int prix;
	private SupportReproduction support;
	


	public Reproduction(int id, Oeuvre oeuvre, int prix, SupportReproduction support) {
		super();
		this.id = id;
		this.oeuvre = oeuvre;
		this.prix = prix;
		this.support = support;

	}



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



	public SupportReproduction getSupport() {
		return support;
	}



	public void setSupport(SupportReproduction support) {
		this.support = support;
	}
	
	
	
	
	
	
	
}
