package com.ald.projet.entities;

import javax.persistence.Entity;


@Entity
public class Photographie extends Oeuvre {
	private Support support;

	public Photographie(){
		
	}
	
	
	public Photographie(Support support) {
		super();
		this.support = support;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}
	
	
}
