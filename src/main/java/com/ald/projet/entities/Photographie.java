package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ald.projet.property.Dimension;
import com.ald.projet.property.SupportOeuvre;


@Entity
public class Photographie extends Oeuvre {
	
	@Enumerated(EnumType.STRING)
	private SupportOeuvre support;

	public Photographie(){
		
	}
	
	
	
	public Photographie(int id, Dimension dimension, Artiste artiste,SupportOeuvre support) {
		super(id, dimension, artiste);
		this.support = support;
	}


	public SupportOeuvre getSupport() {
		return support;
	}

	public void setSupport(SupportOeuvre support) {
		this.support = support;
	}
	
	
}
