package com.ald.projet.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.ald.projet.property.EtatCollection;

@Entity
public class Collection {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Embedded
	private EtatCollection etat;
	
	@ManyToMany
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EtatCollection getEtat() {
		return etat;
	}

	public void setEtat(EtatCollection etat) {
		this.etat = etat;
	}

	public List<Oeuvre> getOeuvres() {
		return oeuvres;
	}

	public void setOeuvres(List<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;
	}
	
	
	
	
}
