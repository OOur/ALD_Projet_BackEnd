package com.ald.projet.entities;

import javax.persistence.Entity;


@Entity
public class Peinture extends Oeuvre{
	private Support support;
	private Realisation realisation;

	public Peinture(){
		
	}
	
	
	public Peinture(Support support, Realisation realisation) {
		super();
		this.support = support;
		this.realisation = realisation;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}

	public Realisation getRealisation() {
		return realisation;
	}

	public void setRealisation(Realisation realisation) {
		this.realisation = realisation;
	}
	
	
}
