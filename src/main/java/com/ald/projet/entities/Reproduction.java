package com.ald.projet.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ald.projet.property.SupportReproduction;

@Entity
public class Reproduction {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	@JoinColumn(name="oeuvre_id", nullable=false)
	private Oeuvre oeuvre;
	private int prix;
	@Enumerated(EnumType.STRING)
	private SupportReproduction support;
	


	public Reproduction(Oeuvre oeuvre, int prix, SupportReproduction support) {
		super();
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
