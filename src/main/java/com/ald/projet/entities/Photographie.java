package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.ald.projet.property.SupportOeuvre;


@Entity
public class Photographie extends Oeuvre {
	
	@Embedded
	private SupportOeuvre support;

	public Photographie(){
		
	}
	
	
	public Photographie(SupportOeuvre support) {
		super();
		this.support = support;
	}

	public SupportOeuvre getSupport() {
		return support;
	}

	public void setSupport(SupportOeuvre support) {
		this.support = support;
	}
	
	
}
