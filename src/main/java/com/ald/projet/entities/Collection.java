package com.ald.projet.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Collection {
	private int id;
	private EtatCollection etat;
	@Transient
	private List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
	
	
	
	public Collection(){
		
	}
	
	public Collection(List<Oeuvre> oeuvres) {
		super();
		this.oeuvres = oeuvres;
	}

	public void addOeuvre(){
		
	}
	
	public void removeOeuvre(){
		
	}
	
	public void addComment(){
		
	}
	
	public void addTag(){
		
	}
	
	public String toString(){
		return null;
	}
}
