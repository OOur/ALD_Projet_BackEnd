package com.ald.projet.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ald.projet.property.Dimension;
import com.ald.projet.property.Realisation;
import com.ald.projet.property.SupportOeuvre;


@Entity
public class Peinture extends Oeuvre{
	@Enumerated(EnumType.STRING)
	private SupportOeuvre support;
	@Enumerated(EnumType.STRING)
	private Realisation realisation;

	
	public Peinture(){
		
	}
	
	
	
	public Peinture(int id, Dimension dimension, Artiste artiste,SupportOeuvre support, Realisation realisation) {
		super(id, dimension, artiste);
		this.support = support;
		this.realisation = realisation;
	}


	public SupportOeuvre getSupport() {
		return support;
	}

	public void setSupport(SupportOeuvre support) {
		this.support = support;
	}

	public Realisation getRealisation() {
		return realisation;
	}

	public void setRealisation(Realisation realisation) {
		this.realisation = realisation;
	}
	
	
}
