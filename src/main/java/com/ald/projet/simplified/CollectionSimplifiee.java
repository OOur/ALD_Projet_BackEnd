package com.ald.projet.simplified;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ald.projet.property.EtatCollection;


@XmlRootElement(name = "collectionsimplifiee")
public class CollectionSimplifiee {

	private int id;
	private EtatCollection etat;	
	private String libele;
	

	public CollectionSimplifiee(){
		
	}
	
	
	public CollectionSimplifiee(int id, String libele ,EtatCollection etat) {
		super();
		this.id = id;
		this.etat = etat;
		this.libele = libele;
	}


	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public EtatCollection getEtat() {
		return etat;
	}

	public void setEtat(EtatCollection etat) {
		this.etat = etat;
	}

	@XmlElement
	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}
	
}
